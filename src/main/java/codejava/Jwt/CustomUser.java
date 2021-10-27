package codejava.Jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import codejava.Entity.*;

import lombok.Data;

@Data
public class CustomUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Account account;

	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUser (Account account, Collection<? extends GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getHashPassword(); 
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
