package codejava.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codejava.Dto.ListproductDto;
import codejava.Dto.cartDto;
import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Entity.roles;
import codejava.Jwt.CustomUser;
import codejava.Jwt.JwtTokenProvider;
import codejava.Services.CartService;
import codejava.Services.ProductsServices;
import codejava.Services.RolesServices;
import codejava.Services.TypeOfProductServices;
import codejava.Constant.RoleConst;
import codejava.Entity.Users;
import codejava.Services.UserServices;
import codejava.Dto.ListproductDto;
@Controller
public class HomeController {
	@Autowired
	ServletContext app;
	@Autowired
	private UserServices userservices;
	@Autowired
	private ProductsServices productsservices;
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	@Autowired
	private CartService cartServices;
	@Autowired
	private RolesServices rolesservices;
	@Autowired
	private TypeOfProductServices typrOfProductSrvcs;

	@Autowired
	private AuthenticationManager  authenManager;
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@GetMapping({"/home","/"})
	public String doGetController(Model model,HttpSession session,@RequestParam("p") Optional<Integer> p) {
		cartDto currentCart = (cartDto) session.getAttribute("currentCart");
		ListproductDto Top4Prod = (ListproductDto) session.getAttribute("Top4Prod");
		if(currentCart == null) {
			session.setAttribute("currentCart", new cartDto());
		}
		if(Top4Prod == null) {
			session.setAttribute("Top4Prod", new ListproductDto());
		}
		List<Products> sp = productsservices.findAll();
		List<TypeOfProduct> listType = typrOfProductSrvcs.getListTypeOfProduct();
		model.addAttribute("listType", listType);
		model.addAttribute("listProduct", sp);
		return "home/index";
	};
	
	@GetMapping("/home/login")
	public String doGetLogin(Model model) {
		model.addAttribute("user", new Users());
		return "home/login";
	};
	@PostMapping("/home/login")
	public String doPostLogin(Model model, @ModelAttribute("user") @Validated Users userlogin,
			HttpSession session) {
		try {
			
		UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(
				userlogin.getUsername(),userlogin.getHashPassword());
		
		Authentication authentication = authenManager.authenticate(authenInfo);
		//Authentication authentication = authenManager.authenticate(authenInfo);
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Users userResponse = userservices.findByUserName(userlogin.getUsername());
		
//		System.out.println(userResponse.getFullname());
//		boolean loginStatus = bcrypt.matches(userlogin.getHashPassword(), userResponse.getHashPassword());
//		System.out.println(loginStatus);
//		if (userResponse != null && loginStatus) {
			roles RoleUserResponse = userResponse.getRole();
			// tạo Sesstion tại Server
			session.setAttribute(SessionConst.CURRENT_USER, userResponse);
			session.setAttribute(SessionConst.CURRENT_ROLE, RoleUserResponse);
			//model.addAttribute("role",RoleUserResponse);
			//return "home/index3";
			session.setAttribute(SessionConst.JWT, tokenProvider.generateToken(customUser));
			session.setAttribute(SessionConst.CURRENT_USER, userResponse);
			return "redirect:/home";
//		} else {
//			String message = "Sai mật khẩu !";
//			model.addAttribute("message", message);
//			System.out.println(message);
//			return "/home/login";
//		}
			}catch(Exception e ) {
			e.printStackTrace();
			String message = "Error! Missing fail";
			model.addAttribute("message", message);
			return "/home/login";
			}
		
		
	}
	@GetMapping("/remove")
	public String doGetRemove(Model model, @RequestParam("id") int userid) {
	//public String doGetRemove(Model model, @PathVariable("id") int userid) {
		return "home/index";
	}
	
	@GetMapping("/home/codes")
	public String doGetCodes() {
		return "home/codes";
	}
	@GetMapping("/home/register")
	public String doGetRegister(Model model) {
		model.addAttribute("newUser", new Users());
		return "home/register";
	}
	
	@PostMapping("register")
	public String doPostRegistration(Model model, @ModelAttribute("newUser") @Validated Users newUser) {
		
		try {
			newUser.setImgUrl("");
			newUser.setIsDeleted(true);
			newUser.setHashPassword(bcrypt.encode(newUser.getHashPassword()));
			roles role = rolesservices.findByID(2);
			newUser.setRole(role);
			userservices.addUser(newUser);
			return "redirect:/home";
		} catch (Exception e) {	
			e.printStackTrace();
			return "redirect:/home/register";
		}
		
	}			  
	@GetMapping("/home/about")
	public String doGetAbout() {
		return "home/about";
	}
	@GetMapping("/home/shipping")
	public String doGetShipping() {
		return "home/shipping";
	}
	@GetMapping("/home/kitchen")
	public String doGetKitchen() {
		return "home/kitchen";
	}
	@GetMapping("/home/care")
	public String doGetCare() {
		return "home/care";
	}
	@GetMapping("/home/hold")
	public String doGetHold() {
		return "home/hold";
	}
	@GetMapping("/home/contact")
	public String doGetContact() {
		return "home/contact";
	}
	@GetMapping("/home/single")
	public String doGetSingle() {
		return "home/single";
	}
	@GetMapping("/home/terms")
	public String doGetTerms() {
		return "home/terms";
	}
	@GetMapping("/home/faqs")
	public String doGetFaqs() {
		return "home/faqs";
	}
	@GetMapping("/home/offer")
	public String doGetOffer() {
		return "home/offer";
	}
	
	
}
