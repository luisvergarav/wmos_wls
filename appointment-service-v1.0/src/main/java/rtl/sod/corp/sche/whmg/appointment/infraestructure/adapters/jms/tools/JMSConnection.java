package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.jms.tools;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.exception.ServerConnectionException;
import weblogic.jms.client.WLConnectionImpl;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * JMSConnection
 * An implementation of WL-JMS 
 */
@Slf4j
public class JMSConnection implements ExceptionListener, MessageListener {

	private static final int MAX_RETRY_COUNT = 5;
    private static final long RECONNECTBLOCKINGTIMEMILLIS 	= 15000L;           // 15 seconds.
    
    private static final long TOTALRECONNECTPERIODMILLIS    = 2L * 36000000L;   // 2 Hours    
    
	@Getter
	private DestinationDefinition destination;
	
	@Getter @Setter
	private String jndiUrl;
	
	@Getter @Setter
	private String contextFactory;

	@Getter @Setter
	private DestinationMode destinationMode;

	private final Object connectionMutex = new Object();
	
	@Getter @Setter
	private ConnectionFactory jmsFactory;
	@Getter @Setter
	private Connection jmsConnection;
	@Getter @Setter
	private Session jmsSession;
	@Getter @Setter
	private Destination jmsDestination;
	@Getter @Setter
	private MessageConsumer messageConsumer;
	@Getter @Setter
	private MessageProducer messageProducer;

	@Getter @Setter
	private MessageListener messageListener;
	// Default session is transactional
	@Getter @Setter
	private boolean sessionTransacted = false;
	@Getter @Setter
	private int sessionAcknowledge = Session.AUTO_ACKNOWLEDGE;

	public JMSConnection() {

	}

	public void init(DestinationDefinition destination) {
		this.destination = destination;
    	this.jndiUrl = destination.getPath();        
    	this.contextFactory = "weblogic.jndi.WLInitialContextFactory";
	}

	protected void createJmsConnection(ConnectionFactory factory) throws JMSException {
		final String userName = destination.getUsername();
		final String password = destination.getPassword();
		if ((userName != null) && (password != null) && (userName.length() > 0) && (password.length() > 0)) {
			jmsConnection = factory.createConnection(userName, password);
		} else {
			log.warn("[" + getDestinationMode()
					+ "] Insecure connection. It's highly recommended to use only authenticated connections.");
			jmsConnection = factory.createConnection();
		}
		
        final WLConnectionImpl wlConnection = (WLConnectionImpl)jmsConnection;
        final String clientId = "JMSConnection_" + getDestinationMode().toString() + "_" + System.currentTimeMillis();
        try{
            wlConnection.setClientID( clientId, WLConnectionImpl.CLIENT_ID_POLICY_UNRESTRICTED );
        }catch(JMSException e){
        	log.error("Can't set the clientID of the WL Connection (" + e.getMessage() + ").");
        }

        wlConnection.setReconnectBlockingMillis(RECONNECTBLOCKINGTIMEMILLIS);
        wlConnection.setTotalReconnectPeriodMillis(TOTALRECONNECTPERIODMILLIS);	
	}

	protected InitialContext getInitialContext() throws NamingException, JMSException {
		final Properties ctxProperties = new Properties();
		ctxProperties.setProperty(InitialContext.INITIAL_CONTEXT_FACTORY, contextFactory);
		ctxProperties.setProperty(InitialContext.PROVIDER_URL, jndiUrl);
		final String userName = destination.getUsername();
		final String password = destination.getPassword();
		if ((userName != null) && (password != null) && (userName.length() > 0) && (password.length() > 0)) {
			ctxProperties.setProperty(InitialContext.SECURITY_PRINCIPAL, userName);
			ctxProperties.setProperty(InitialContext.SECURITY_CREDENTIALS, password);
		}
		return new InitialContext(ctxProperties);
	}

	protected Destination getJmsDestination(InitialContext ctx) throws NamingException, JMSException {
		return (Destination) ctx.lookup(destination.getResourceName());
	}

	protected ConnectionFactory getJmsConnectionFactory(InitialContext ctx) throws NamingException, JMSException {
		return (ConnectionFactory) ctx.lookup(destination.getFactoryName());
	}

	public void start(final DestinationMode mode) throws ServerConnectionException {
		setDestinationMode( mode );

		if (log.isDebugEnabled()) {
			log.debug("[" + getDestinationMode() + "] Connecting to destination " + destination.getResourceName() + ".");
		}
		try {
			final InitialContext ctx = getInitialContext();

			jmsFactory = getJmsConnectionFactory(ctx);
			
			createJmsConnection(jmsFactory);
			jmsConnection.setExceptionListener(this);

			jmsSession = jmsConnection.createSession(sessionTransacted, sessionAcknowledge);

			jmsDestination = getJmsDestination(ctx);

			if (mode == DestinationMode.PUBLISHER) {
				messageProducer = jmsSession.createProducer(jmsDestination);
				if (log.isDebugEnabled()) {
					log.debug("[" + getDestinationMode() + "] Producer ready!.");
				}
			}
			if (mode == DestinationMode.SUSCRIBER) {
				String selector = destination.getSelector();
				if ((selector == null) || ("".equals(selector))) {
					messageConsumer = jmsSession.createConsumer(jmsDestination);
				} else {
					messageConsumer = jmsSession.createConsumer(jmsDestination, selector);
				}
				messageConsumer.setMessageListener(this);
				if (log.isDebugEnabled()) {
					log.debug("[" + getDestinationMode() + "] Consumer ready!.");
				}
			}

			jmsConnection.start();

		} catch (NamingException | JMSException e) {
			throw new ServerConnectionException(e);
		}
	}

	public void stop() throws ServerConnectionException{
		if (log.isDebugEnabled()) {
			log.debug("[" + getDestinationMode() + "] Stopping destination.");
		}

		// Remove exception listener
		try {
			if (jmsConnection != null) {
				jmsConnection.setExceptionListener(null);
			}
			if (messageProducer != null) {
				messageProducer.close();
			}
			if (messageConsumer != null) {
				messageConsumer.close();
			}

			if (jmsSession != null) {
				jmsSession.close();
			}

			if (jmsConnection != null) {
				jmsConnection.stop();
				jmsConnection.close();
			}
			
		} catch (final JMSException e) {
			throw new ServerConnectionException(e);
		}
	}

	public TextMessage createTextMessage() throws ServerConnectionException {
		try {
			return jmsSession.createTextMessage();
		} catch (final JMSException e) {
			throw new ServerConnectionException("Can't create a new message.", e);
		}
	}

	@Synchronized("connectionMutex")
	public void publish(final Message message) throws ServerConnectionException {
		if (log.isDebugEnabled()) {
			log.debug("[" + getDestinationMode() + "] Publishing message.");
		}

		// Retry on publish error
		int retryCount = 0;
		Exception lastException = null;
		while (retryCount < MAX_RETRY_COUNT) {
			try {
				if ((sessionTransacted) && (retryCount > 0)) {
					jmsSession.rollback();
				}
				messageProducer.send(message);
				lastException = null;
				break;
			} catch (JMSException e) {
				retryCount++;
				lastException = e;
			}
		}
		if (lastException != null) {
			throw new ServerConnectionException(lastException);
		}
	}

	@Synchronized("connectionMutex")
	public void commit() throws ServerConnectionException {
		if ((messageConsumer != null) && (messageListener == null)) {
			final ServerConnectionException e = new ServerConnectionException(
					"Can't commit a connection without listener or consumer.");
			throw new ServerConnectionException(e);
		}
		if (sessionTransacted) {
			try {
				jmsSession.commit();
			} catch (final JMSException e) {
				if (!(e.getMessage().contains("Interrupted"))) {
					throw new ServerConnectionException("Can't commit a message due " + e.getMessage(), e);
				}
			}
		}
	}
	
	@Override
	public void onException(JMSException e) {
	    log.error(e.getMessage(),e);
	}

	@Override
	public void onMessage(Message message) {
		if (messageListener!=null) {
			messageListener.onMessage(message);
		}else {
			try {
				log.info("Received: " + message.getJMSMessageID());
			}catch( JMSException e ) {
				log.error(e.getMessage(),e);
			}
		}
	}	
}
