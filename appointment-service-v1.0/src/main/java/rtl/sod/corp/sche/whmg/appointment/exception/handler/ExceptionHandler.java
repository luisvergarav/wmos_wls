package rtl.sod.corp.sche.whmg.appointment.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import rtl.sod.corp.sche.whmg.appointment.domain.APIResponse;
import rtl.sod.corp.sche.whmg.appointment.exception.MDWRestException;
import rtl.sod.corp.sche.whmg.appointment.rest.RestConstants;


/**
 * Customized handler to get the message from rest exception;
 * 
 * @author: jameswang
 * @version: 1.0, Feb 6, 2018
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<MDWRestException> {

	/**
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(MDWRestException pException) {
		APIResponse res = new APIResponse();
		res.setCode(RestConstants.ERROR_CODE);
		res.setType(RestConstants.SYSTEM_ERROR);
		res.setMessage(pException.getMessage());
		return Response.ok().entity(res).build();
	}

}
