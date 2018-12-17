package rtl.sod.corp.sche.whmg.appointment.application.adapters;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import rtl.sod.corp.sche.whmg.appointment.application.ports.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.application.ports.NotifyAppointmentCommand;
import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;
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

	
			Message<AppointmentReq> message = new Message<AppointmentReq>();
			NotifyAppointmentCommand<AppointmentReq> command  = (NotifyAppointmentCommand<AppointmentReq>)cmd;
			message.setPayload(command.getPayload());
			client.notify(message);
		
		
		
	}

}
