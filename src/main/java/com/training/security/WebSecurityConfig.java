package com.training.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@Order(value = 103)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;
	@Autowired
	DataSource dataSource;
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
//	@Bean
//	public UserDetailsService userDetailsService() {
//	    return new CustomUserDetailsService();
//	}
	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.authenticationProvider(authenticationProvider());
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/login","/home", "/index","/","/css/**", "/js/**", "/images/**","/plugins/**","/api/search/{pageNumber}", "/product/api/brandList").permitAll()
					.antMatchers("/brand","/product").access("hasRole('ROLE_ADMIN')")
					.anyRequest().authenticated()
			.and().formLogin().loginPage("/login")
					.loginProcessingUrl("/loginAction")
					.defaultSuccessUrl("/product")
					.usernameParameter("username")
					.passwordParameter("password")
					.failureUrl("/login?error")
			.and().logout().logoutSuccessUrl("/login").deleteCookies("JSESSIONID", "remember-me").invalidateHttpSession(true)
			.and().exceptionHandling().accessDeniedPage("/login")
			.and().rememberMe()
				.rememberMeCookieName("remember-me")
				.tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(1 * 24 * 60 * 60)
			.and().csrf().disable();
		
			
		}
	

	 @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	       JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
	       tokenRepositoryImpl.setDataSource(dataSource);
	       
	       return tokenRepositoryImpl;
		}
}