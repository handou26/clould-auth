package com.jd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jd.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	  private static final String SECRETPREFIX = "{noop}"; //spring security5 之后需要
	
	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;

	    
	   // 定义认证用于信息获取来源以及密码校验规则
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	
	        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
				
				@Override
				public boolean matches(CharSequence rawPassword, String encodedPassword) {
					
					return new BCryptPasswordEncoder().encode(SECRETPREFIX+rawPassword).toString().equals(encodedPassword);
				}
				
				@Override
				public String encode(CharSequence rawPassword) {
					return new BCryptPasswordEncoder().encode(SECRETPREFIX+rawPassword).toString();
				}
			});
	    }

	    //定义拦截规则
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                .anyRequest().authenticated()
	                .antMatchers("/oauth/**").permitAll()
	                .and()
	                .csrf().disable();
	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/css/**", "/js/**", "/plugins/**", "/favicon.ico");
	    }

	    
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	    
//	    @Bean
//	    public BCryptPasswordEncoder encoder(){
//	        return new BCryptPasswordEncoder();
//	    }

	    //跨域解决
	    @Bean
	    public FilterRegistrationBean<CorsFilter> corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	        bean.setOrder(0);
	        return bean;
	    }
}
