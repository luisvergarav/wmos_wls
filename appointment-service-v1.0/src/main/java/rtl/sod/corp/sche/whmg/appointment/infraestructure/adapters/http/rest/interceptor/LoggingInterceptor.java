package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;

import org.apache.log4j.MDC;

import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Interceptor
@Slf4j
public class LoggingInterceptor {

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		log.debug("Started the interceptor.");
		MDC.put(RestConstants.SERVICE_REF_MDC, ctx.getMethod().getName());
		long tsStart = System.currentTimeMillis();
		
	
		
		for (Map.Entry<String, Object> entry : ctx.getContextData().entrySet()) {
			log.info("Item : " + entry.getKey() + " Count : " + entry.getValue());
		}
		
		try {
			return ctx.proceed();
		} finally {
			long tsStop = System.currentTimeMillis();
			log.debug("txEpd=" + String.valueOf(tsStop - tsStart));
			MDC.remove(RestConstants.SERVICE_REF_MDC);
			log.debug("Ended the interceptor.");
			
		}
	}
}