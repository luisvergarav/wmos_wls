package rtl.sod.corp.sche.whmg.appointment.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;
import org.reflections.Reflections;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ApplicationPath(RestConstants.API_PATH)
@Slf4j
public class RestActivator extends Application {

	@Getter // getClasses() from Application.class
	private final Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestActivator() {
		super();

		// Swagger
		classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		// Services
		addServicesAndApis(classes);
	}



	private void addServicesAndApis(Set<Class<?>> resources) {
		Reflections classes = new Reflections(RestConstants.SERVICE_BASE_PACKAGE);

		for (Class<?> clazz : classes.getTypesAnnotatedWith(Api.class)) {
			if (!resources.contains(clazz)) {
				log.info("Adding service: " + clazz.getName());
				resources.add(clazz);
			}
		}
		
		for (Class<?> clazz : classes.getTypesAnnotatedWith(Provider.class)) {
			if (!resources.contains(clazz)) {
				log.info("Adding Provider: " + clazz.getName());
				resources.add(clazz);
			}
		}
	}
}
