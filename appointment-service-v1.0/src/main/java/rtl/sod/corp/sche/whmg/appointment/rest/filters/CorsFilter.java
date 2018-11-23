package rtl.sod.corp.sche.whmg.appointment.rest.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@WebFilter(filterName = "HTML5CorsFilter", urlPatterns = { "/api/*" })
@Slf4j
public class CorsFilter implements Filter {

	public CorsFilter() {
		log.info("CorsFilter initialized...");
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		res.addHeader("Access-Control-Allow-Headers", "Content-Type");
		chain.doFilter(request, response);
	}



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}



	@Override
	public void destroy() {
	}

}
