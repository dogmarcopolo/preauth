package com.jfwang.preauth;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.addFilterAfter(new myAbstractPreAuthenticatedProcessingFilter(), RequestHeaderAuthenticationFilter.class)     
            .authorizeRequests()      
            .antMatchers("/", "/Token").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN") 
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/both/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .userInfoEndpoint();
            //.oidcUserService(oidcUserService);
    }
    
/*
 * test user/oassword authentication, just wrap CustomUserDetailsServices to disable autowired
 * otherwise authentication chain will break
 */

/* 
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http.authorizeRequests()
	            .antMatchers("/", "/Token").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN") 
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/both/**").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated() 
				.and() 
    			.formLogin().permitAll()    				
    			//.loginPage("/login")
				.and()
    			.logout().permitAll();
    	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.authorities("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
*/

}