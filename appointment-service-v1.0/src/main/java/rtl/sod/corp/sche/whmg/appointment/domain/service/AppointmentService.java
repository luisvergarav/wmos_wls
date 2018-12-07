package rtl.sod.corp.sche.whmg.appointment.domain.service;


import rtl.sod.corp.sche.whmg.appointment.client.AppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.client.wls.service.WlsAppointmentClient;
import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;

import javax.inject.Inject;

public class AppointmentService {
	
	@Inject
	private  AppointmentClient client;

	public AppointmentService() {
	
	}

	public void notify(AppointmentReq request) throws Exception {
		Message<AppointmentReq> message = new Message<AppointmentReq>();
		message.setPayload(request);
		client.notify(message);
	}

}