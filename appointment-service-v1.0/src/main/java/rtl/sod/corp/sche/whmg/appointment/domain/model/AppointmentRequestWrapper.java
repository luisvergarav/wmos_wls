package rtl.sod.corp.sche.whmg.appointment.domain.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentRequestWrapper {
	
		@XmlElement(name = "AppointmentReq")
		AppointmentReq appointmentReq;

	public AppointmentReq getAppointmentReq() {
		return appointmentReq;
	}

	public void setAppointmentReq(AppointmentReq appointmentReq) {
		this.appointmentReq = appointmentReq;
	}

	
	
	
}