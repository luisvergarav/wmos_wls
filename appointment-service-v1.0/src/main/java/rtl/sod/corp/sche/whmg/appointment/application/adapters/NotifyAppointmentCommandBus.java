package rtl.sod.corp.sche.whmg.appointment.application.adapters;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import rtl.sod.corp.sche.whmg.appointment.domain.ports.Command;
import rtl.sod.corp.sche.whmg.appointment.domain.ports.CommandBus;

@Default
public class NotifyAppointmentCommandBus implements CommandBus {

	NotifyAppointmentHandler handler;

	@Inject
	public NotifyAppointmentCommandBus(NotifyAppointmentHandler handler) {
		super();
		this.handler = handler;
	}


	@Override
	public void execute(Command command) throws Exception {
	 	handler.handle(command);
		
	}




    
}