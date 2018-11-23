package rtl.sod.corp.sche.whmg.appointment.jms.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import corp.common.e2e.core.E2EContext;
import corp.common.e2e.core.E2EHelperNotFoundException;
import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.exception.MDWRestException;
import rtl.sod.corp.sche.whmg.appointment.exception.ServerConnectionException;
import rtl.sod.corp.sche.whmg.appointment.rest.RestAPPConfig;
import rtl.sod.corp.sche.whmg.appointment.rest.RestConstants;

/**
 * this is tools to build JMS message and Send the message to JMS
 * 
 * @author: jameswang
 * @version: 1.0, Feb 7, 2018
 */
@Slf4j
public class JMSMessageTools {

	private static final RestAPPConfig	CONFIG				= new RestAPPConfig();



	private static DestinationDefinition getServerDefition() {
		DestinationDefinition destination = new DestinationDefinition();
		destination.setPath(CONFIG.getProperty(RestConstants.JMS_PATH));
		destination.setUsername(CONFIG.getProperty(RestConstants.JMS_USER_NAME));
		destination.setPassword(CONFIG.getProperty(RestConstants.JMS_PASSWORD));
		log.info("getServerDefition, destination: {}.", destination);
		return destination;
	}



	private static DestinationDefinition getTopicDefinition() {
		DestinationDefinition destination = getServerDefition();
		destination.setFactoryName(CONFIG.getProperty(RestConstants.JMS_FACTORY));
        destination.setResourceName(CONFIG.getProperty(RestConstants.JMS_TOPIC));
		destination.setResourceType(ResourceTypeDefinition.TOPIC);
		log.info("getTopicDefinition, destination: {}.", destination);
		return destination;
	}



	public static void sendJMS(Map<String, String> pJmsHeaders, String pMessage) throws MDWRestException {
		JMSConnection connection = new JMSConnection();
		try {
			connection.init(getTopicDefinition());
			connection.start(DestinationMode.PUBLISHER);
			TextMessage msg = connection.createTextMessage();
			
			E2EContext e2e = new E2EContext();
			try {
				e2e.getE2EContext(msg);
			} catch (E2EHelperNotFoundException e) {
				log.error("Splunk E2EContext Jms Header Exception {} !", e);
				
			}
			
			
			msg.setText(pMessage);
			log.info("sendJMS, message: {},header: {}.", pMessage, pJmsHeaders);
            if (pJmsHeaders != null) {
                Iterator<Entry<String, String>> it = pJmsHeaders.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, String> entry = it.next();
                    msg.setStringProperty(entry.getKey(), entry.getValue());
                }
            }
			connection.publish(msg);
			connection.commit();
		} catch (JMSException | ServerConnectionException e) {
			log.error("JMSConnection published get exception for message {} !", e, pMessage);
			throw new MDWRestException("Send JMS failed", e);
		} finally {
			try {
				connection.stop();
			} catch (ServerConnectionException e) {
				log.error("JMSConnection stoped failed for message {} !", e, pMessage);
				throw new MDWRestException("Send JMS failed", e);
			}
		}
	}



	public static int verfSendJMS(Map<String, String> pJmsHeaders, String pMessage) {
		int flag = 0;
		try {
			sendJMS(pJmsHeaders, pMessage);
		} catch (Exception e) {
			flag = 1;
		}
		return flag;
	}

}
