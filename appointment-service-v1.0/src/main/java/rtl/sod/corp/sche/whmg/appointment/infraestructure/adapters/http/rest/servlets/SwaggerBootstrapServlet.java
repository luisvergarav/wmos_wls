package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.jaxrs.config.BeanConfig;
import lombok.Getter;
import rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.RestConstants;

@WebServlet(urlPatterns="/swaggerbootstrap", loadOnStartup=2 )
public class SwaggerBootstrapServlet extends HttpServlet{
	private static final long serialVersionUID = -7305578658465900497L;
	
	@Getter
	private transient BeanConfig beanConfig;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0");
		beanConfig.setBasePath( config.getServletContext().getContextPath() + RestConstants.API_PATH);
		beanConfig.setResourcePackage( RestConstants.SERVICE_BASE_PACKAGE );
		beanConfig.setScan(true);
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus( HttpServletResponse.SC_NOT_FOUND );
	}
}