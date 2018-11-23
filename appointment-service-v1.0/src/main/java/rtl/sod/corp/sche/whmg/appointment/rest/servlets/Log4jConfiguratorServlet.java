package rtl.sod.corp.sche.whmg.appointment.rest.servlets;


import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import lombok.extern.slf4j.Slf4j;

@WebServlet(
		urlPatterns="/Log4jConfiguratorServlet",
		loadOnStartup=0
	)
@Slf4j
public class Log4jConfiguratorServlet extends HttpServlet {

	private static final long serialVersionUID = -3989014460586727854L;

	public Log4jConfiguratorServlet() {

	}
	
	@Override
	public void init( ServletConfig config ) throws ServletException {
		InputStream logProperties = getClass().getResourceAsStream( "/log4j.properties" );
		PropertyConfigurator.configure( logProperties );
		
		log.info("Logger properly configured.");
	}

}
