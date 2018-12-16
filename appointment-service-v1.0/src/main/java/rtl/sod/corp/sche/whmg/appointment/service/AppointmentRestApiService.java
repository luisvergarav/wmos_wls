package rtl.sod.corp.sche.whmg.appointment.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import corp.common.e2e.core.E2EContext;
import corp.common.e2e.core.E2EHelperNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import rtl.sod.corp.sche.whmg.appointment.application.adapters.NotifyAppointmentCommandBus;
import rtl.sod.corp.sche.whmg.appointment.application.adapters.NotifyAppointmentImpl;
import rtl.sod.corp.sche.whmg.appointment.application.ports.NotifyAppointmentCommand;
import rtl.sod.corp.sche.whmg.appointment.domain.APIResponse;
import rtl.sod.corp.sche.whmg.appointment.domain.model.AppointmentRequestWrapper;
import rtl.sod.corp.sche.whmg.appointment.rest.RestConstants;
import rtl.sod.corp.sche.whmg.appointment.rest.interceptor.Logged;

@Path("/")
@Api(value = "Wmos API")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AppointmentRestApiService {
	@Context
	private transient HttpHeaders headers;


	
	
	@PUT
	@Logged
	@Path("/api/wmos/v1.0/appointment")
	@ApiOperation(value = "Notify Appointments", notes = "This method ...", response = APIResponse.class)
	public Response addAppointment(@ApiParam("Appointment") @NotNull @Valid AppointmentRequestWrapper request)
			throws IllegalArgumentException, UnknownHostException {

		E2EContext e2e = new E2EContext();
		try {
			e2e.setE2EContext(headers);
		} catch (E2EHelperNotFoundException e) {
			log.error("Error E2EContext setting headers");
			
		}
		e2e.setServiceRef("Appointment");

			log.info("appointment request.", request);
			try{
				NotifyAppointmentCommandBus cmdBus = new NotifyAppointmentCommandBus();
				
				
				NotifyAppointmentCommand cmd = new NotifyAppointmentImpl(request.getAppointmentReq());
				
				cmdBus.execute(cmd);
				
				log.info("Request Appointment successful!", request.getAppointmentReq().getHeader().getReferenceID());
			}catch(Exception ex)
			{
	
				log.error("Request Appointment Error!", request.getAppointmentReq().getHeader().getReferenceID());
				return  Response.ok().entity(this.buildErrorRes(ex.getLocalizedMessage())).build();
			}

		return Response.ok().entity(buildSuccessRes()).build();
		}

	

	private APIResponse buildSuccessRes() {
		APIResponse res = new APIResponse();
		res.setCode(RestConstants.SUCCESS_CODE);
		res.setType(RestConstants.SUCCESS_RESPONSE);
		return res;
	}
	
	/**
	 * API Error response
	 *
	 * @return
	 */
	private APIResponse buildErrorRes(String error) {
		APIResponse res = new APIResponse();
		res.setCode(RestConstants.ERROR_CODE);
		res.setType(RestConstants.SYSTEM_ERROR);
		res.setMessage(error);
		return res;
	}
}
