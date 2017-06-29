package com.giorgiofederici.sjp.config;

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

}
