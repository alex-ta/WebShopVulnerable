package configurationAnnotation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import database.datatypes.Role;
import services.UserService;


// configure security and database check properites and beans
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  extends
   WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserService userService;
	  
  // Add password de/encoding
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
    //.passwordEncoder(passwordHash());
  }
  
  @Bean
  public PasswordEncoder passwordHash(){
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/register","/about").permitAll()
        .antMatchers("/user/**").hasRole(""+Role.USER)
        .antMatchers("/worker/**").hasRole(""+Role.WORKER)
        .antMatchers("/admin/**").hasRole(""+Role.ADMIN)
        .antMatchers("/root/**").hasRole(""+Role.ROOT)
        .anyRequest().hasRole("ANONYMOUS").and()
        .formLogin().loginPage("/login").loginProcessingUrl("/signin").permitAll()
        .failureUrl("/login?error")
        .defaultSuccessUrl("/admin")
		.usernameParameter("username")
		.passwordParameter("password").and().
		logout().logoutUrl("/logout")
		.logoutSuccessUrl("/login?logout").and()
		.exceptionHandling().accessDeniedPage("/error/403");
  }
}

