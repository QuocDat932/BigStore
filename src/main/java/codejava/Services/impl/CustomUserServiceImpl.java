package codejava.Services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import codejava.Entity.Account;
import codejava.Entity.Users;
import codejava.Jwt.CustomUser;
import codejava.Responsitory.Accountrepo;
import codejava.Services.*;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
	
	/*
	 * @Autowired private UserServices userService;
	 */
	@Autowired
	private AccountService AccService;

	@Override
    public UserDetails loadUserByUsername(String username) {
		Account acc = AccService.findByUsername(username);
    	if (acc == null) {
            throw new UsernameNotFoundException(username);
        }
    	try {
    		List<GrantedAuthority> grantList = new ArrayList<>();
    		GrantedAuthority authority = new SimpleGrantedAuthority(acc.getUsers().getRole().getDescription());
    		grantList.add(authority);
            return new CustomUser(acc, grantList);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
	
}
