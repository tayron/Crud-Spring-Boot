package br.com.tutorial.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/institutions/**").hasAuthority("USER")
				.antMatchers("/students/**").hasAuthority("USER")
				.antMatchers("/users/**").hasAuthority("USER")
				.antMatchers("/roles/**").hasAuthority("USER")
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/authentication/login")
				.failureUrl("/authentication/login-error")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/authentication/access-denied");
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        	.ignoring()
        	.antMatchers(
    			"/webjars/**", 
    			"/resources/**", 
    			"/static/**", 
    			"/css/**", 
    			"/js/**", 
    			"/images/**"
			);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.
            jdbcAuthentication()
            .usersByUsernameQuery(usersQuery)
            .authoritiesByUsernameQuery(rolesQuery)
            .dataSource(dataSource)
            .passwordEncoder(encoder);
    }    
    
/*	
    @Bean
    @Override        
    public UserDetailsService userDetailsService() {    	
    	
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	System.out.println(encoder.encode("admin"));
    	
    	String pass = "{bcrypt}$2a$10$PuQ43EaoFM7xYTzu.BMW9ec/s3jSwsETKP0PVIAwHRc8QkGDDAij6";
    	
   	 	UserDetails user = User.withUsername("user")
		 .password(pass)
   			 .roles("USER")
   			 .build();
   	         
        return new InMemoryUserDetailsManager(user);             
    }	
*/
}
