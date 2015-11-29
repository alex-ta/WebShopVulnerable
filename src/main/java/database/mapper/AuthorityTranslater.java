package database.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
@Component
public class AuthorityTranslater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 01102015L;
	private List<String> authoritiesOrdered;
	private String defaultUser;
	
	public AuthorityTranslater(){}

	public List<String> getAuthoritiesOrdered() {
		return authoritiesOrdered;
	}

	public void setAuthoritiesOrdered(List<String> authoritiesOrdered) {
		this.authoritiesOrdered = authoritiesOrdered;
	}

	public String getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
	

	public Collection<? extends GrantedAuthority> decorateAuthoriy(String role) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(authoritiesOrdered.indexOf(role) != -1){
			int size = authoritiesOrdered.size();
			for(int i= size-1; i>0 && !authoritiesOrdered.get(i).equals(role); i--){
				list.add(new SimpleGrantedAuthority(authoritiesOrdered.get(i)));
			}
			list.add(new SimpleGrantedAuthority(role));
		}
		// always return first Role
		list.add(new SimpleGrantedAuthority(defaultUser));
		return list;
	}

}