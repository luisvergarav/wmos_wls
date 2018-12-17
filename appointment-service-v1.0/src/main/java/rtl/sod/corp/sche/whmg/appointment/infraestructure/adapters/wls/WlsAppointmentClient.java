package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.wls;

import java.util.Map;
import javax.enterprise.inject.Default;

import rtl.sod.corp.sche.whmg.appointment.application.ports.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.jms.tools.JMSMessageTools;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.tools.MessageService;
import lombok.extern.slf4j.Slf4j;

@Default
@Slf4j
public class WlsAppointmentClient implements AppointmentClient {


	@Override
	public void notify(String requestJMSMessage, Map<String, String> jmsHeaders) throws Exception {
		
		
		MessageService.getInstance().validateMessage(requestJMSMessage);
		
		String requestXML = MessageService.getInstance().transformMessage(requestJMSMessage);

		
		log.info("Sending Appointment...! " + requestXML,requestXML);
		JMSMessageTools.sendJMS(jmsHeaders, requestXML);

		

	}

}