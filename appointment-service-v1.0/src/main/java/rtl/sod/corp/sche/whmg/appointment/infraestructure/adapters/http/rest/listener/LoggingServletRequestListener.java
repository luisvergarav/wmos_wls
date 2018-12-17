package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.listener;

import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@WebListener
public class LoggingServletRequestListener implements ServletRequestListener {
	@Override
	public void requestInitialized(ServletRequestEvent pServletRequestEvent) {
		setMDCs(pServletRequestEvent);
	}



	@Override
	public void requestDestroyed(ServletRequestEvent pServletRequestEvent) {
		MDC.clear();
	}



	private void setMDCs(ServletRequestEvent pServletRequestEvent) {
		ServletRequest servletRequest = pServletRequestEvent.getServletRequest();
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
			setMDC(httpRequest, RestConstants.TRANSACTION_REF_HEADER, RestConstants.TRANSACTION_REF_MDC,
					() -> UUID.randomUUID().toString(), null);
			setMDC(httpRequest, RestConstants.CONSUMER_REF_HEADER, RestConstants.CONSUMER_REF_MDC,
					() -> StringUtils.EMPTY, null);
			setMDC(httpRequest, RestConstants.NODE_REF_HEADER, RestConstants.NODE_REF_MDC, () -> {
				try {
					return InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException e) {
					log.warn("Could not get the host name.", e);
					return StringUtils.EMPTY;
				}
			}, null);
		}
	}



	private void setMDC(HttpServletRequest request, String nameInHeader, String nameInMDC,
			Supplier<String> initValueSupplier, Function<String, String> newValueSupplier) {
		String headerValue = StringUtils.EMPTY;
		if (StringUtils.isEmpty(request.getHeader(nameInHeader))) {
			if (initValueSupplier != null) {
				headerValue = initValueSupplier.get();
			}
			log.debug("There was not value of the key {} in the HTTP header. Give an initial value {}.", nameInHeader,
					headerValue);
		} else {
			headerValue = request.getHeader(nameInHeader);
			if (newValueSupplier != null) {
				headerValue = newValueSupplier.apply(headerValue);
				log.debug("A new calculated value was provided {}.", headerValue);
			}
		}
		log.debug("Put value {} to key {}.", headerValue, nameInHeader);
		MDC.put(nameInMDC, headerValue == null ? StringUtils.EMPTY : headerValue);
	}
}
