package com.cognicx.AppointmentRemainder.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognicx.AppointmentRemainder.hmac.HmacFilter;
import com.cognicx.AppointmentRemainder.jwt.JwtAuthEntryPoint;
import com.cognicx.AppointmentRemainder.jwt.JwtAuthTokenFilter;
import com.cognicx.AppointmentRemainder.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Value("${app.auth.enabled}")
	private boolean authEnabled;

	/*
	 * @Autowired private JwtAuthEntryPoint unauthorizedHandler;
	 */
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	/*
	 * @Bean public HmacFilter authenticationHmacFilter() { return new HmacFilter();
	 * }
	 */

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (authEnabled) {

			http.cors().and().csrf().disable().authorizeRequests()
					.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**",
							"/ruleEngine/executeRules", "/ruleEngine/getUniqueVariables",
							"/ruleEngine/validateRuleUtil", "/ruleEngine/eligibleAmountRuleUtil")
					.permitAll().anyRequest().authenticated().and().exceptionHandling()
					.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		} else {
			http.cors().and().csrf().disable().authorizeRequests().antMatchers("/ruleEngine/**").permitAll().and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

	}	

}
