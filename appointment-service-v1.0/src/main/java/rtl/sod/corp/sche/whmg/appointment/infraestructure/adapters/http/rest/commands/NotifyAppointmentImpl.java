package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.commands;


import rtl.sod.corp.sche.whmg.appointment.application.ports.NotifyAppointmentCommand;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;

import javax.enterprise.inject.Default;


@Default
public class NotifyAppointmentImpl implements NotifyAppointmentCommand {
	
	
	AppointmentReq request;

	public NotifyAppointmentImpl(AppointmentReq request) {
		super();
		this.request = request;
	}

	public AppointmentReq getRequest() {
		return request;
	}

	public void setRequest(AppointmentReq request) {
		this.request = request;
	}

	@Override
	public Object getPayload() {
		return request;
	}



	

}