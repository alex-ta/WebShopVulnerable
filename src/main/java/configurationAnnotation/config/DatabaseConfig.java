package configurationAnnotation.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
@Configuration
public class DatabaseConfig {
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    sessionBuilder.scanPackages("database.tables");
	    sessionBuilder.addProperties(getHibernateProperties());
	    return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
             Properties prop = new Properties();
             prop.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
             prop.put("hibernate.current_session_context_class","thread");
             prop.put("hibernate.hbm2ddl.auto","update");
             prop.put("hibernate.search.default.directory_provider","filesystem");
             // do not forget to give tomecat user & rights
             prop.put("hibernate.search.default.indexBase", "/var/lucene/indexes");
             
             // remove later
             prop.put("hibernate.show_sql","true");
             return prop;
     }
	
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource source = new BasicDataSource();
	    source.setDriverClassName("org.postgresql.Driver");
		source.setUrl("jdbc:postgresql://127.0.0.1:5432/webdata");
		source.setUsername("webapp");
		source.setPassword("webapp");
		source.setInitialSize(2);
		source.setMaxActive(5);
		return source;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
		
}
