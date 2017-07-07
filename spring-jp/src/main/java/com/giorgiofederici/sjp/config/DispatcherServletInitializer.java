package com.giorgiofederici.sjp.config;

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

}
