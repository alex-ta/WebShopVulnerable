package configurationAnnotation.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import criteriaSearch.AdvancedSearch;
import criteriaSearch.NormalSearch;
import criteriaSearch.TextSearch;
import database.tables.Product;

@Configuration
public class SearchConfig {
	  @Bean
	  public AdvancedSearch advancedSearch() {
		  AdvancedSearch search = new AdvancedSearch();
		  Map<String,String> fields = new HashMap<String,String>();
		  fields.put("Price", "slider");
		  fields.put("Order", "objects");
		  fields.put("Textil", "header");
		  fields.put("Size",  "objects");
		  fields.put("Color", "objects");
		  fields.put("Category", "objects");
		  fields.put("Material", "objects");
		  fields.put("Print", "header");
		  fields.put("Name", "objects");
		  fields.put("Color", "objects");
		  fields.put("Category", "objects");
		  
		  search.setMap(fields);
		  return search;
	  }
	 
	  @Bean
	  public NormalSearch normalSearch() {
		  NormalSearch search = new NormalSearch();
		  Map<String,String> fields = new HashMap<String,String>();
		  fields.put("Price", "slider");
		  fields.put("Size",  "objects");
		  fields.put("Print", "header");
		  
		  search.setMap(fields);
		  return search;
	  }
	 
	 @SuppressWarnings("rawtypes")
	 @Bean
	 @Autowired
	 public TextSearch Search(SessionFactory sessionFactor){
		 return new TextSearch(sessionFactor, Product.class);
	 }
}
