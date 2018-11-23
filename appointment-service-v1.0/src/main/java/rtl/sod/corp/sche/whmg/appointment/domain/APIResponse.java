package rtl.sod.corp.sche.whmg.appointment.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * API response
 * 
 * @author: jameswang
 * @version: 1.0, Feb 2, 2018
 */
@Data
public class APIResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631579479199449062L;

	@JsonPropertyDescription("response code.")
	private int					code;

	@JsonPropertyDescription("response type.")
	private String				type;

	@JsonPropertyDescription("response message.")
	private String				message;

}
