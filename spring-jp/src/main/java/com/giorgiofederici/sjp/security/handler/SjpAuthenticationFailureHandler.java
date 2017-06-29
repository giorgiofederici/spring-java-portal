package com.giorgiofederici.sjp.security.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class SjpAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private MessageSource messages;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		setDefaultFailureUrl("/signin?error");

		super.onAuthenticationFailure(request, response, exception);

		Locale locale = this.localeResolver.resolveLocale(request);
		String errorMessage = this.messages.getMessage("sjp.signin.validation.user.invalid", null, locale);

		if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			this.messages.getMessage("sjp.signin.validation.user.invalid", null, locale);
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			errorMessage = this.messages.getMessage("sjp.signin.validation.user.disabled", null, locale);
		}

		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}

}
