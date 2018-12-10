package rtl.sod.corp.sche.whmg.appointment.application.client;


import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;
import rtl.sod.corp.sche.whmg.appointment.domain.ports.messaging.AppointmentService;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.wls.WlsAppointmentClient;

import javax.inject.Inject;

public class AppointmentServiceImpl implements AppointmentService {
	
	
	private  AppointmentClient client;
	@Inject
	public AppointmentServiceImpl(AppointmentClient client) {
		this.client = client;
	}

	public void notify(AppointmentReq request) throws Exception {
		Message<AppointmentReq> message = new Message<AppointmentReq>();
		message.setPayload(request);
		client.notify(message);
	}

}