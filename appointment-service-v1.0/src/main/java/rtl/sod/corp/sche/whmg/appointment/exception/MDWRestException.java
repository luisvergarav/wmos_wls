package rtl.sod.corp.sche.whmg.appointment.exception;

import javax.ws.rs.WebApplicationException;

/**
 * MDW rest customized exception.
 * 
 * @author: jameswang
 * @version: 1.0, Feb 7, 2018
 */

public class MDWRestException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public MDWRestException() {
		 // This constructor is intentionally empty. Nothing special is needed here.
	}



	public MDWRestException(String pMessage) {
		super(pMessage);
	}



	public MDWRestException(Throwable pCause) {
		super(pCause);
	}



	public MDWRestException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
