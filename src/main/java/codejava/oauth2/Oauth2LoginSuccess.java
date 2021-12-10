package codejava.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import codejava.Constant.SessionConst;
import codejava.Constant.publicFuncs;
import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Services.AccountGGService;
import codejava.Services.AccountService;
import codejava.Services.RolesServices;
import codejava.Services.UserServices;
import java.util.Objects;

@Component
public class Oauth2LoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private AccountGGService accountGGSerive;
	
	
	@Autowired
	private UserServices userSerive;
	
	@Autowired
	private RolesServices role;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		CustomOAuth2User a = (CustomOAuth2User) authentication.getPrincipal();
		String email = a.getEmail();
		String idgg = a.getSub();
		Users account = accountGGSerive.findByEmailAndIdaccount(email, idgg);
		
		HttpSession session = request.getSession();
		if (!Objects.isNull(account)) {
			System.out.println(account.getRole().getId());
			roles rolesUserGG = role.findByID(account.getRole().getId());
			
			session.setAttribute(SessionConst.CURRENT_USER, account);
			session.setAttribute(SessionConst.CURRENT_ROLE, rolesUserGG);
			System.out.println("da dk");
		} else {
			Users users = new Users();
			users.setFullname(a.getFullName());
			users.setEmail(a.getEmail());
			users.setIsDeleted(true);
			users.setRole(role.findByID(2));
			users.setType_account("GG");
			userSerive.addUser(users);
			
			AccountGG accountGG = new AccountGG() ;
			accountGG.setEmail(a.getEmail());
			accountGG.setIdaccount(a.getSub());
			accountGG.setUsers(userSerive.findByEmail(a.getEmail()));
			accountGG.setDelete(true);
			accountGGSerive.addAccount(accountGG);
			
			account = accountGGSerive.findByEmailAndIdaccount(email, idgg);
			if (!Objects.isNull(account)) {
			roles rolesUserGG = role.findByID(account.getRole().getId());
			session.setAttribute(SessionConst.CURRENT_USER, account);
			session.setAttribute(SessionConst.CURRENT_ROLE, rolesUserGG);
			}else {
			System.out.println("chua dang ki");
			}
		}

		super.onAuthenticationSuccess(request, response, authentication);

	}

}
