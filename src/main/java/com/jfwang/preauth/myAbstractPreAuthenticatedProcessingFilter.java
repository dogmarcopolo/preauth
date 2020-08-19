package com.jfwang.preauth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class myAbstractPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	public myAbstractPreAuthenticatedProcessingFilter() {
		setAuthenticationManager(authenticationManager());
	}
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		/*
		 * TODO: whatever is needed to validate this request as pre-authenticated
		 */
		System.err.println("getPreAuthenticatedPrincipal");
		String s = request.getParameter("test");
		if (s != null) return s; else return null;


	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		/*
		 * TODO: whatever is needed to validate this request as pre-authenticated
		*/
		String s = request.getParameter("test");
		if (s != null) return s; else return null;
	}


	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Bean
	protected AuthenticationManager authenticationManager()  {
		final List<AuthenticationProvider> providers = new ArrayList<>(1);
		providers.add(preauthAuthProvider());
		return new ProviderManager(providers);
	}
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean(name = "preAuthProvider")
	PreAuthenticatedAuthenticationProvider preauthAuthProvider()  {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(customUserDetailsService);
		return provider;
	}

}
