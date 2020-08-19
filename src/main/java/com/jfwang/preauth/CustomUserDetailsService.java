package com.jfwang.preauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService extends PreAuthenticatedGrantedAuthoritiesUserDetailsService {

	@Override
	protected UserDetails createUserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities) {
		/*
		 * TODO: replace with ladp, database, etc query to get user roles/authorities
		 */
		System.err.println("CustomUserDetailsService");
		// Collection<GrantedAuthority> authorities = new ArrayList<>();
 	   	//authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		UserDetails details = new org.springframework.security.core.userdetails.User("jihfang", "", authorities);
		return details;		
	}
 
}