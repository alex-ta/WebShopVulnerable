package criteriaSearch;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class NormalSearch {
	
	private Map<String,String> map;
	
	public NormalSearch(){}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
