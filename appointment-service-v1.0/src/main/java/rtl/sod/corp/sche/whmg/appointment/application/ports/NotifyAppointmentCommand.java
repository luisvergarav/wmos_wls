package rtl.sod.corp.sche.whmg.appointment.application.ports;

import rtl.sod.corp.sche.whmg.appointment.domain.ports.Command;

public interface NotifyAppointmentCommand<AppointmentReq> extends Command{

	public AppointmentReq getPayload();
}
