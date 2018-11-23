package rtl.sod.corp.sche.whmg.appointment.exception.handler;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import rtl.sod.corp.sche.whmg.appointment.domain.APIResponse;
import rtl.sod.corp.sche.whmg.appointment.rest.RestConstants;


/**
 * Validation exception handler
 * 
 * @author: jameswang
 * @version: 1.0, Feb 6, 2018
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	/**
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ConstraintViolationException pException) {
		APIResponse res = new APIResponse();
		res.setCode(RestConstants.ERROR_CODE);
		res.setType(RestConstants.VALIDATE_ERROR);
		Set<ConstraintViolation<?>> errors = pException.getConstraintViolations();
		if (!errors.isEmpty()) {
			res.setMessage(errors.iterator().next().getMessage());
		}
		return Response.ok().entity(res).build();
	}

}
