package rtl.sod.corp.sche.whmg.appointment.application.adapters;

import rtl.sod.corp.sche.whmg.appointment.domain.ports.Command;
import rtl.sod.corp.sche.whmg.appointment.domain.ports.CommandBus;

public class NotifyAppointmentCommandBus implements CommandBus {

	@Override
	public void execute(Command command) throws Exception {
		command.execute();
		
	}




    
}