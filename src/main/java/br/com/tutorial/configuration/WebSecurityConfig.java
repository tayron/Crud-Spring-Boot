package br.com.tutorial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	
//    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    	System.out.println(encoder.encode("admin"));
    	
    	String pass = "{bcrypt}$2a$10$PuQ43EaoFM7xYTzu.BMW9ec/s3jSwsETKP0PVIAwHRc8QkGDDAij6";
    	
   	 	UserDetails user = User.withUsername("user")
		 .password(pass)
   			 .roles("USER")
   			 .build();
   	         
        return new InMemoryUserDetailsManager(user);
             
    }	

}
