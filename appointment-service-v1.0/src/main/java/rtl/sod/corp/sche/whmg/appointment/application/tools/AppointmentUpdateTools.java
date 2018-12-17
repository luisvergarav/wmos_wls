package rtl.sod.corp.sche.whmg.appointment.application.tools;

import java.util.HashMap;
import java.util.Map;

import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;
import lombok.extern.slf4j.Slf4j;


/**
 * Order update tools to generate request xml, it invokes the freemarker to
 * generate the requested xml.
 * 
 * @author: jameswang
 * @version: 1.0, Feb 8, 2018
 */
@Slf4j
public class AppointmentUpdateTools {
	

    /**
    * <method description>
    *
    * @param pOrderId
    * @param pOrderInfo
    * @return
    */
    
    public static Map<String, String> buildOrderUpdateJmsHeaders(AppointmentReq request) {
        log.debug("appointmentUpdateJmsHeaders order id: {}.");
        Map<String, String> result = new HashMap<>();
        //result.put(RestConstants.JMS_SELECTOR_CODE, pOrderId);
        //result.put(RestConstants.JMS_XTXREF, request.getHeader().getSequenceNumber().toString());
        //result.put(RestConstants.JMS_XCMREF, request.getHeader().getSource());
        //result.put(RestConstants.JMS_XRHSREF, request.getHeader().getHostName());
        //result.put(RestConstants.JMS_USRTX, pOrderId);
        //result.put(RestConstants.JMS_COUNTRY, request.getCountry());
        //result.put(RestConstants.JMS_COMMERCE, request.getCommerce());
        log.debug("appointmentUpdateJmsHeaders,  id: {}, get the jms headers {}.", result);
        return result;
    }

    

}