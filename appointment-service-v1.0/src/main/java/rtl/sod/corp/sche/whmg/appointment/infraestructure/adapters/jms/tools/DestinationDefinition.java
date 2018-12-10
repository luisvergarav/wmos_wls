package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.jms.tools;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author cesar.pasache@latam.com
 */
@Data
public class DestinationDefinition implements Serializable {
	private static final long		serialVersionUID	= -7986994240103973296L;

	private String					path;

	private String					username;
	private String					password;

	private String					factoryName;

	private ResourceTypeDefinition	resourceType		= ResourceTypeDefinition.QUEUE;

	private String					resourceName;

	private String					selector;

}