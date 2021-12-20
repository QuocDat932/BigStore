package codejava.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codejava.Dto.ListproductDto;
import codejava.Dto.cartDto;
import codejava.Entity.Account;
import codejava.Entity.AccountGG;
import codejava.Entity.Orders;
import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Entity.roles;
import codejava.Jwt.CustomUser;
import codejava.Jwt.JwtTokenProvider;
import codejava.Responsitory.Userrepository;
import codejava.Services.AccountService;
import codejava.Services.CartService;
import codejava.Services.Orderservices;
import codejava.Services.ProductsServices;
import codejava.Services.RolesServices;
import codejava.Services.TypeOfProductServices;
import codejava.Constant.RoleConst;
import codejava.Entity.Users;
import codejava.Services.UserServices;
import net.bytebuddy.utility.RandomString;
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
	private Orderservices orderServices;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AuthenticationManager authenManager;
	@Autowired
	private JwtTokenProvider tokenProvider;

	@GetMapping({ "/home", "/" })
	public String doGetController(Model model, HttpSession session, @RequestParam("p") Optional<Integer> p) {
		cartDto currentCart = (cartDto) session.getAttribute("currentCart");
		ListproductDto Top4Prod = (ListproductDto) session.getAttribute("Top4Prod");
		if (currentCart == null) {
			session.setAttribute("currentCart", new cartDto());
		}
		if (Top4Prod == null) {
			session.setAttribute("Top4Prod", new ListproductDto());
		}
		List<Products> sp = productsservices.findProductAvai(1);

		List<TypeOfProduct> listType = typrOfProductSrvcs.getListTypeOfProduct();
		
	
		model.addAttribute("listType", listType);
		model.addAttribute("listProduct", sp);
		
		return "home/index";
	};

	@GetMapping("/home/login")
	public String doGetLogin(Model model) {
		List<TypeOfProduct> listType = typrOfProductSrvcs.getListTypeOfProduct();
		model.addAttribute("listType", listType);
		model.addAttribute("user", new Account());

		return "home/login";
	};

	@GetMapping("/login")
	public String doGetLogi1(Model model,HttpSession session) {
		return "redirect:/home";
	};

	@PostMapping("/home/login")
	public String doPostLogin(Model model, @ModelAttribute("user") @Validated Account accountLogin,
			HttpSession session) {
		try {
			/* accountLogin.setIsDeleted(false); */
			UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(
					accountLogin.getUsername(), accountLogin.getHashPassword());

			Authentication authentication = authenManager.authenticate(authenInfo);
			CustomUser customUser = (CustomUser) authentication.getPrincipal();
			
			Account accountResponse = accountService.findByUsername(accountLogin.getUsername());
			
			
			if(accountResponse.getUsers().getIsDeleted()) {
				Users usersResponse  =  accountResponse.getUsers() ;
				roles RoleUserResponse = usersResponse.getRole();
				// tạo Sesstion tại Server
				session.setAttribute(SessionConst.CURRENT_USER, usersResponse);
				session.setAttribute(SessionConst.CURRENT_ROLE, RoleUserResponse);
				session.setAttribute(SessionConst.JWT, tokenProvider.generateToken(customUser));
				
				return "redirect:/home";
			} else {
				String message = "Error! Missing fail : This Account Had Be Deleted";
				model.addAttribute("message", message);
				return "/home/login";
			}

		} catch (Exception e) {
			e.printStackTrace();
			String message = "Error! Missing fail : Please Try Again";
			model.addAttribute("message", message);
			return "/home/login";
		}

	}

	@GetMapping("/remove")
	public String doGetRemove(Model model, @RequestParam("id") int userid) {
		// public String doGetRemove(Model model, @PathVariable("id") int userid) {
		return "home/index";
	}

	@GetMapping("/home/logout")
	public String doGetLogout(Model model, HttpSession session) {
		session.removeAttribute(SessionConst.CURRENT_CART);
		session.removeAttribute(SessionConst.CURRENT_ROLE);
		session.removeAttribute(SessionConst.CURRENT_USER);
		return "redirect:/home";
	};

	@GetMapping("/home/codes")
	public String doGetCodes() {
		return "home/codes";
	}

	@GetMapping("/home/about")
	public String doGetAbout() {
		return "home/about";
	}

	@GetMapping("/home/OrderHistory")
	public String doGetOrderHistory(HttpSession session) {
		// List<Orders> result = orderServices.findByParam(4 ,"UserIdAndPaymentMethod",
		// 2, null);

		if(session.getAttribute(SessionConst.CURRENT_USER) == null) {
			return "redirect:/home/register";
		}else {
		
		return "home/orderHist";}
	}

	@GetMapping("/home/shipping")
	public String doGetShipping() {
		return "home/shipping";
	}

	@GetMapping({ "/type", "/type/{slug}" })
	public String doGetKitchen(Model model, HttpSession session, Optional<String> slug) {
		cartDto currentCart = (cartDto) session.getAttribute("currentCart");
		ListproductDto Top4Prod = (ListproductDto) session.getAttribute("Top4Prod");

		if (Objects.nonNull(typrOfProductSrvcs.findBySlug(slug.orElse("null")))) {
			return "redirect:/home";
		}

		List<TypeOfProduct> listType = typrOfProductSrvcs.getListTypeOfProduct();
		model.addAttribute("listType", listType);
		return "home/kitchen";
	}

	@GetMapping("/home/care")
	public String doCare() {
		return "home/care";
	}

	@GetMapping("/home/{slug}")
	public String doGetSingle(@PathVariable Optional<String> slug, Model model, Model model2, HttpSession session) {
		Products p = productsservices.findByProductsSlug(slug.get());

		TypeOfProduct Type = p.getTypeOfProduct();

		List<Products> listP = productsservices.findByTypeOfProduct(Type);
		/*
		 * if(Type listP) {
		 * 
		 * }
		 */
		listP.remove(p.getSlug());

		model.addAttribute("Product", p);
		model2.addAttribute("lisType", listP);

		return "home/single";

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
