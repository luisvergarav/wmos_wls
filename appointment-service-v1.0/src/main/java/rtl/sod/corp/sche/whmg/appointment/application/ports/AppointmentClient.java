package rtl.sod.corp.sche.whmg.appointment.application.ports;

import java.util.Map;

import rtl.sod.corp.sche.whmg.appointment.domain.message.Message;


public interface AppointmentClient {

    void notify( final String requestJMSMessage, Map<String, String> jmsHeaders ) throws Exception;    
}