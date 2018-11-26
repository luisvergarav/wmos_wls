package rtl.sod.corp.sche.whmg.appointment.client.wls.service;

import java.util.Map;


import rtl.sod.corp.sche.whmg.appointment.client.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.jms.tools.JMSMessageTools;
import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;
import rtl.sod.corp.sche.whmg.appointment.tools.MessageService;
import rtl.sod.corp.sche.whmg.appointment.tools.AppointmentUpdateTools;
import rtl.sod.corp.sche.whmg.appointment.tools.xml.XmlConvert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WlsAppointmentClient implements AppointmentClient {


	@Override
	public void notify(Message message) throws Exception {
		
		XmlConvert<AppointmentReq> convert = new XmlConvert<AppointmentReq>();
		
		String requestJMSMessage = convert.convert((AppointmentReq)message.getPayload());

		MessageService.getInstance().validateMessage(requestJMSMessage);
		
		String requestXML = MessageService.getInstance().transformMessage(requestJMSMessage);

		Map<String, String> jmsHeaders = AppointmentUpdateTools.buildOrderUpdateJmsHeaders((AppointmentReq)message.getPayload());

		log.info("Sending Appointment...! " + requestXML,requestXML);
		JMSMessageTools.sendJMS(jmsHeaders, requestXML);

		

	}

}