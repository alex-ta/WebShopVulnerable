package database.mapper;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import database.tables.User;
import pojo.basket.Basket;
@Component
public class SpringUser implements UserDetails{
	
	/**
	 * created Date
	 */
	private static final long serialVersionUID = 28082015L;
	@Autowired
	private AuthorityTranslater authTrans;
	private User user;
	private Basket basket;
	
	public SpringUser(User user){
		this.user = user;
		this.basket = new Basket(user);
	}
	
	public SpringUser(){
		this.basket = new Basket(user);
	}

	public String getRealname(){
		return user.getFirstname();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authTrans.decorateAuthoriy("ROLE_"+user.getRole());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.isNull()){
			return false;
		}
		return !user.getExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.isNull()){
			return false;
		}
		return !user.getLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(this.isNull()){
			return false;
		}
		return user.getEmailValid();
	}

	@Override
	public boolean isEnabled() {
		if(this.isNull()){
			return false;
		}
		return user.getEnabled();
	}
	public AuthorityTranslater getAuthTrans() {
		return authTrans;
	}
	public void setAuthTrans(AuthorityTranslater authTrans) {
		this.authTrans = authTrans;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isNull() {
		return (user==null);
	}
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
