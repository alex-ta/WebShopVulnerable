package configurationAnnotation.config;

import java.util.LinkedList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import database.mapper.AuthorityTranslater;
import database.mapper.SpringUser;

@Configuration
@ComponentScan({"database.mapper","database.dao","services"})
@Import(DatabaseConfig.class) 
public class RootConfig {

	  @Bean
	  public AuthorityTranslater authorityTranslater() {
		  AuthorityTranslater translater = new AuthorityTranslater();
		  LinkedList<String>authorities = new LinkedList<String>();
		  authorities.add("ROLE_ROOT");
		  authorities.add("ROLE_ADMIN");
		  authorities.add("ROLE_WORKER");
		  authorities.add("ROLE_USER");
		  translater.setDefaultUser("ROLE_ANONYMOUS");
		  translater.setAuthoritiesOrdered(authorities);
		  return translater;
	  }
	  
	  @Bean 
	  @Scope("prototype")
	  public SpringUser user(){
		  return new SpringUser();
	  }
}
