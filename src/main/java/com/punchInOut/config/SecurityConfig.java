package com.punchInOut.config;

	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()	
					.antMatchers("/**").hasAnyRole("USER","ADMIN").anyRequest().authenticated().and()		
				.formLogin().loginPage("/login")
		        .permitAll()
					.loginPage("/login").failureUrl("/login-error").defaultSuccessUrl("/",true);	
			http.csrf().disable();
		}

	
	}

