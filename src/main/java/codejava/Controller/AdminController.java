package codejava.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codejava.Constant.RoleConst;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Jwt.CustomUser;
import codejava.Services.StatsServices;
import codejava.Services.UserServices;
import codejava.Constant.publicConst;

@RequestMapping("/admin")
@Controller(value = "AdminController")
public class AdminController {
	@Autowired
	private StatsServices adminSrvs;
	@Autowired
	private UserServices userSrvs;
	@Autowired
	private AuthenticationManager  authenManager;
	@Autowired
	private UserServices userservices;
	@GetMapping("/login")
	public String doGetLogin(Model model, HttpSession session) {
		System.out.println("Admin Login");
		model.addAttribute("user", new Users());
		model.addAttribute("message","login to continue");
		return "admin/login";
	}
	@PostMapping("/login")
	public String doPostLogin(Model model, @ModelAttribute("user") @Validated Users userlogin,
								HttpSession session ) {
		try {
			UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(
					userlogin.getUsername(),userlogin.getHashPassword());
			Authentication authentication = authenManager.authenticate(authenInfo);
			CustomUser customUser = (CustomUser) authentication.getPrincipal();
			Users userResponse = userservices.findByUserName(userlogin.getUsername());
			roles RoleUserResponse = userResponse.getRole();
			if(RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_ADMIN) ||
			   RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_MANAGER))
			{
				session.setAttribute(SessionConst.CURRENT_USER, userResponse);
				session.setAttribute(SessionConst.CURRENT_ROLE, RoleUserResponse);
				return "layout/indexAdmin";
			}
			else {
				model.addAttribute("message", "You are not allow");
				return "/admin/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Login failure");
			return "/admin/login";
		}
		
	}
	@GetMapping("/logout")
	public String doGetLogout(Model model, HttpSession session) {
		session.removeAttribute(SessionConst.CURRENT_USER);
		session.removeAttribute(SessionConst.CURRENT_ROLE);
		session.removeAttribute(SessionConst.CURRENT_CART);
		model.addAttribute("message","Login to continue");
		return "redirect:/admin/login";
	}
	@GetMapping({"/home","/"})
	public String doGetIndexAdmin(Model model) {
		//** Code Here
		//...
		
		//return "layout/indexAdmin";
		return "redirect:/admin/login";
	};
	@GetMapping("/dashboard")
	public String doGetdashboard(Model model) {
		//** Code Here
		//...
		
		return "admin/index";
	};
	@GetMapping("/forms")
	public String doGetForm(Model model) {
		//** Code Here
		//...
		
		return "admin/basic-forms";
	};
	@GetMapping("/product/productMgt")
	public String doGetProduct(Model model) {
		//** Code Here
		//...
		
		return "admin/Product-productMgt";
	};
	@GetMapping("/product/categotyMgt")
	public String doGetCategory(Model model) {
		//** Code Here
		//...
		
		return "admin/Product-categoryMgt";
	};
	@GetMapping("/user/user-userMgt")
	public String doGetUser(Model model) {
		//** Code Here
		//...
		String[][] dataUs = adminSrvs.getcountUs();
		List<Users> users = userSrvs.findAll();
		model.addAttribute("dataUs", dataUs);
		model.addAttribute("users", users);
		return "admin/User-userMgt";
	};
	@GetMapping("/user/user-userVipMgt")
	public String doGetUserVip(Model model) {
		//** Code Here
		//...
		return "admin/User-userVipMgt";
	};
	@GetMapping("/ChartOrder")
	public String doGetChartOrder(Model model) {
		//** Code Here
		//...
				
		return "admin/ChartOrder";
	};
	@GetMapping("/ListOrder")
	public String doGetListOrder(Model model) {
		//** Code Here
		//...
						
		return "admin/ListOrder";
	}
	
}
