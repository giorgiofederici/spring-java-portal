package com.giorgiofederici.sjp.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.jfree.chart.servlet.DisplayChart;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Specifies the configuration classes for the root application context
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootApplicationContextConfig.class };
	}

	/*
	 * Specifies the configuration classes for the Dispatcher servlet
	 * application context
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebApplicationContextConfig.class };
	}

	/*
	 * Specifies the servlet mappings for DispatcherServlet
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		// Without this Servlet, all jfree charts will get 404 error
		ServletRegistration.Dynamic jfreeChartServlet = servletContext.addServlet("jfreeChart", new DisplayChart());
		jfreeChartServlet.setLoadOnStartup(2);
		jfreeChartServlet.addMapping("/jfree-chart/*");
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
	}

	/* Set these variables for your project needs */

	private static final String LOCATION = "";

	private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;// 25MB

	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;// 30MB

	private static final int FILE_SIZE_THRESHOLD = 0;

}
