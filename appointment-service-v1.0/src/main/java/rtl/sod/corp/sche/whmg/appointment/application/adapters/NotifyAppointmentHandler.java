package rtl.sod.corp.sche.whmg.appointment.application.adapters;

import java.util.Map;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import rtl.sod.corp.sche.whmg.appointment.application.ports.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.application.ports.NotifyAppointmentCommand;
import rtl.sod.corp.sche.whmg.appointment.application.tools.AppointmentUpdateTools;
import rtl.sod.corp.sche.whmg.appointment.application.tools.xml.XmlConvert;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;
import rtl.sod.corp.sche.whmg.appointment.domain.ports.Command;
import rtl.sod.corp.sche.whmg.appointment.domain.ports.Handler;

@Default
public class NotifyAppointmentHandler implements Handler {

	AppointmentClient client;
	
	@Inject
	public NotifyAppointmentHandler(AppointmentClient client) {
		this.client = client;
	}

	
	@Override
	public void handle(Command cmd) throws Exception {

	
		
			
			NotifyAppointmentCommand<AppointmentReq> command  = (NotifyAppointmentCommand<AppointmentReq>)cmd;
			
			
			XmlConvert<AppointmentReq> convert = new XmlConvert<AppointmentReq>();
			
			String requestJMSMessage = convert.convert((AppointmentReq)command.getPayload());

			Map<String, String> jmsHeaders = AppointmentUpdateTools.buildOrderUpdateJmsHeaders((AppointmentReq)command.getPayload());

			//message.setPayload(command.getPayload());
			client.notify(requestJMSMessage, jmsHeaders);
		
		
		
	}

}
