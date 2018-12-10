package rtl.sod.corp.sche.whmg.appointment.domain.ports.messaging;

import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;

public interface AppointmentService {
	public void notify(AppointmentReq request) throws Exception;
}
