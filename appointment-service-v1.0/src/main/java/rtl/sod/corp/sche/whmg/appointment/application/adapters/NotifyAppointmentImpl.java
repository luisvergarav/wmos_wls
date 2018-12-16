package rtl.sod.corp.sche.whmg.appointment.application.adapters;


import rtl.sod.corp.sche.whmg.appointment.application.ports.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.application.ports.NotifyAppointmentCommand;
import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;

import javax.inject.Inject;

public class NotifyAppointmentImpl implements NotifyAppointmentCommand {
	
	
	AppointmentReq request;
	
	@Inject
	AppointmentClient client;
	
	public NotifyAppointmentImpl(AppointmentReq request) {
		this.request = request;
	}

	
	
	public void execute() throws Exception {
		Message<AppointmentReq> message = new Message<AppointmentReq>();
		message.setPayload(request);
		client.notify(message);
	}

}