import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rtl.sod.corp.sche.whmg.appointment.application.adapters.NotifyAppointmentCommandBus;
import rtl.sod.corp.sche.whmg.appointment.application.adapters.NotifyAppointmentHandler;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq;
import rtl.sod.corp.sche.whmg.appointment.domain.model.ChannelTYPE;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq.ClientService;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq.Message;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentReq.Message.Appointment;
import rtl.sod.corp.sche.whmg.appointment.domain.model.CommerceTYPE;
import rtl.sod.corp.sche.whmg.appointment.domain.model.CountryTYPE;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.commands.NotifyAppointmentImpl;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.jms.tools.JMSMessageTools;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.wls.WlsAppointmentClient;

@RunWith(MockitoJUnitRunner.class)

public class ApplicationUnitTest {
	private JAXBContext jaxbContext;
    private Unmarshaller unmarshaller;

	

	 @Before
	    public void init() throws JAXBException {
	        this.jaxbContext = JAXBContext.newInstance(AppointmentReq.class);
	        this.unmarshaller = jaxbContext.createUnmarshaller();
	        this.unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
	    }
	 
	@Test
	public void shouldSendMessaje() {	
		WlsAppointmentClient client = new WlsAppointmentClient();
		NotifyAppointmentHandler handler = new NotifyAppointmentHandler(client);
		NotifyAppointmentCommandBus cmdBus = new NotifyAppointmentCommandBus(handler);
		String request = "{" + 
				"   \"AppointmentReq\" : {" + 
				"      \"ClientService\" : {" + 
				"         \"Country\" : \"AR\"," + 
				"         \"Commerce\" : \"Banco\"," + 
				"         \"Channel\" : \"SRX\"" + 
				"      }," + 
				"      \"Header\" : {" + 
				"         \"Source\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Action_Type\" : \"String\"," + 
				"         \"Sequence_Number\" : \"String\"," + 
				"         \"Batch_ID\" : \"aaaaaaaaaa\"," + 
				"         \"Reference_ID\" : \"String\"," + 
				"         \"User_ID\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Password\" : \"String\"," + 
				"         \"Message_Type\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Company_ID\" : \"aaaaaaaaa\"," + 
				"         \"Msg_Locale\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Msg_Time_Zone\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Version\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"         \"Send_Message\" : \"String\"," + 
				"         \"Carrier_Company\" : \"String\"," + 
				"         \"Sequence\" : \"String\"" + 
				"      }," + 
				"      \"Message\" : {" + 
				"         \"Appointment\" : [ {" + 
				"            \"Appointment_ID\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Appointment_Type\" : \"Live Unload\"," + 
				"            \"Facility_Alias_ID\" : \"aaaaaaaaaaaaaaaa\"," + 
				"            \"Appointment_Status\" : \"Unscheduled\"," + 
				"            \"Seal_Number\" : \"String\"," + 
				"            \"Carrier_Code\" : \"aaaaaaaaaa\"," + 
				"            \"Scheduled_Dttm\" : \"String\"," + 
				"            \"Actual_CheckIn_Dttm\" : \"String\"," + 
				"            \"Check_Out_Dttm\" : \"String\"," + 
				"            \"Load_Unload_Start_Dttm\" : \"String\"," + 
				"            \"Load_Unload_End_Dttm\" : \"String\"," + 
				"            \"Appointment_priority\" : \"5\"," + 
				"            \"Driver_Duration_In_Min\" : \"String\"," + 
				"            \"Control_number\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Requestor_name\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Requestor_type\" : \"String\"," + 
				"            \"Reserved_appt_id\" : \"String\"," + 
				"            \"Tp_Company_Id\" : \"String\"," + 
				"            \"Appt_Reason_Id\" : \"String\"," + 
				"            \"Appt_Reason_Code\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Trailer_Duration_In_Min\" : \"123456789\"," + 
				"            \"Driver_ID\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Cancel_Reason_code\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Communication_Mode\" : \"String\"," + 
				"            \"Appointment_Comments\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Business_partner\" : \"aaaaaaaaaa\"," + 
				"            \"Cancel_Indicator\" : \"String\"," + 
				"            \"Load_position\" : \"Nose\"," + 
				"            \"Business_Unit\" : \"aaaaaaaaa\"," + 
				"            \"Requested_Time\" : \"aaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"template_Id\" : \"String\"," + 
				"            \"Cancelled_Tc_Appt_Id\" : \"String\"," + 
				"            \"Appointment_Source\" : \"String\"," + 
				"            \"Load_Configuration\" : \"aaa\"," + 
				"            \"Equipment_ID\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Equipment_Code\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Tractor_ID\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"Tractor_Type\" : \"String\"," + 
				"            \"Equipment\" : \"String\"," + 
				"            \"Dock_Id\" : \"String\"," + 
				"            \"Door_Id\" : \"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\"," + 
				"            \"ApptObject\" : [ {" + 
				"               \"Object_Type\" : \"10\"," + 
				"               \"Object_ID\" : \"aaaaaaaaaaaaaaaaaaaa\"," + 
				"               \"Stop_Seq\" : \"1234\"," + 
				"               \"Purchase_Order\" : [ {" + 
				"                  \"Booked_Size\" : \"0\"," + 
				"                  \"Size_UOM\" : \"String\"," + 
				"                  \"TC_Order_Id\" : \"String\"" + 
				"               }, {" + 
				"                  \"Booked_Size\" : \"1\"," + 
				"                  \"Size_UOM\" : \"12\"," + 
				"                  \"TC_Order_Id\" : \"123\"" + 
				"               }, {" + 
				"                  \"Booked_Size\" : \"3\"," + 
				"                  \"Size_UOM\" : \"123\"," + 
				"                  \"TC_Order_Id\" : \"121233\"" + 
				"               } ]" + 
				"            } ]," + 
				"            \"Appointment_Slots\" : [ {" + 
				"               \"Slot_Id\" : \"String\"," + 
				"               \"Slot_Start_Dttm\" : \"String\"," + 
				"               \"Slot_Duration\" : \"0\"," + 
				"               \"Capacity_Used\" : \"0\"" + 
				"            } ]," + 
				"            \"Custom_Attribute\" : [ {" + 
				"               \"Attribute_Name\" : \"String\"," + 
				"               \"Attribute_Value\" : \"String\"" + 
				"            } ]" + 
				"         } ]" + 
				"      }" + 
				"   }" + 
				"}" + 
				"";
		try {
			
		
	        StringReader reader = new StringReader(request);
	        
	        AppointmentReq appointmentReq = (AppointmentReq) this.unmarshaller.unmarshal(reader);
		
	        NotifyAppointmentImpl cmd = new NotifyAppointmentImpl(appointmentReq);
		
			cmdBus.execute(cmd);
		}catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
