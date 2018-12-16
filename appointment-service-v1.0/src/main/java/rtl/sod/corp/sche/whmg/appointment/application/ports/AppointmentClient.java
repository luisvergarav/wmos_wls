package rtl.sod.corp.sche.whmg.appointment.application.ports;

import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;


public interface AppointmentClient {

    void notify( final Message message ) throws Exception;    
}