package codejava.oauth2;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

	private OAuth2User oauth2;
	
	
	
	public CustomOAuth2User(OAuth2User oauth2) {
 		this.oauth2 = oauth2;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return oauth2.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return oauth2.getAuthorities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oauth2.getAttribute("name");
	}
	
	public String getFullName() {
		return oauth2.getAttribute("name");
	}
	
	public String getEmail() {
		return oauth2.getAttribute("email");
	}
	public String getSub() {
		return oauth2.getAttribute("sub");
	}
}
