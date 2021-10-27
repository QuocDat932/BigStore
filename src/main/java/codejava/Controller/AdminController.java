package codejava.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import codejava.Dto.Message;
import codejava.Dto.productDto;
import codejava.Entity.*;
import codejava.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import codejava.Constant.RoleConst;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Jwt.CustomUser;
import codejava.Constant.publicConst;

@RequestMapping("/admin")
@Controller(value = "AdminController")
public class AdminController {
	@Autowired
	private StatsServices adminSrvs;
	@Autowired
	private UserServices userSrvs;
	@Autowired
	private AuthenticationManager authenManager;
	@Autowired
	private UserServices userservices;
	@Autowired
	private ProductsServices prodServices;
	@Autowired
	private TypeOfProductServices typeServices;
	@Autowired
	private UnitTypeServices unitServices;
	@Autowired
	private AccountService accountServices;
	
	@GetMapping("/login")
	public String doGetLogin(Model model, HttpSession session) {
		System.out.println("Admin Login");
		model.addAttribute("user", new Account());
		model.addAttribute("message", "Login to continue");
		return "admin/login";
	}

	@PostMapping("/login")
	public String doPostLogin(Model model, @ModelAttribute("user") @Validated Account accountLogin, HttpSession session) {
		try {
			UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(
					accountLogin.getUsername(), accountLogin.getHashPassword());
			Authentication authentication = authenManager.authenticate(authenInfo);
			CustomUser customUser = (CustomUser) authentication.getPrincipal();
			
			Account userAccount = accountServices.findByUsername(accountLogin.getUsername());
			
			Users userResponse     = userAccount.getUsers();
			roles RoleUserResponse = userAccount.getUsers().getRole();
			
			if (RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_ADMIN)
					|| RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_MANAGER)) {
				session.setAttribute(SessionConst.CURRENT_ADMIN, userResponse);
				session.setAttribute(SessionConst.CURRENT_ROLE, RoleUserResponse);
				return "redirect:/admin/dashboard";
			} else {
				model.addAttribute("message", "You are not allow");
				return "/admin/login";
			}
		} catch (Exception e) {

			model.addAttribute("message", "Login failure");
			return "/admin/login";
		}

	}

	@GetMapping("/logout")
	public String doGetLogout(Model model, HttpSession session) {
		session.removeAttribute(SessionConst.CURRENT_ADMIN);
		session.removeAttribute(SessionConst.CURRENT_ROLE);
		model.addAttribute("message","Login to continue");
		return "redirect:/admin/login";
	}

	@GetMapping({ "/home", "/" })
	public String doGetIndexAdmin(Model model) {
		// ** Code Here
		// ...

		// return "layout/indexAdmin";
		return "redirect:/admin/login";
	};

	@GetMapping("/dashboard")
	public String doGetdashboard(Model model) {
		// ** Code Here
		// ...

		return "admin/index";
	};

	@GetMapping("/forms")
	public String doGetForm(Model model) {
		// ** Code Here
		// ...

		return "admin/basic-forms";
	};

	@GetMapping("/product/productMgt")
	public String doGetProduct(Model model) {
		List<Products> prod = prodServices.findAll();
		List<TypeOfProduct> type = typeServices.findAll();
		List<UnitType> unit = unitServices.findAll();
		model.addAttribute("prod", prod);
		model.addAttribute("type", type);
		model.addAttribute("unit", unit);
		return "admin/Product-productMgt";
	};

	@GetMapping("/product/categotyMgt")
	public String doGetCategory(Model model) {
		// ** Code Here
		// ...

		return "admin/Product-categoryMgt";
	};

	@GetMapping("/user/user-userMgt")
	public String doGetUser(Model model) {
		// ** Code Here
		// ...
		String[][] dataUs = adminSrvs.getcountUs();
		List<Users> users = userSrvs.findAll();
		model.addAttribute("dataUs", dataUs);
		model.addAttribute("users", users);
		return "admin/User-userMgt";
	};

	@GetMapping("/user/user-userVipMgt")
	public String doGetUserVip(Model model) {
		// ** Code Here
		// ...
		return "admin/User-userVipMgt";
	};

	@GetMapping("/ChartOrder")
	public String doGetChartOrder(Model model) {
		// ** Code Here
		// ...

		return "admin/ChartOrder";
	};

	@GetMapping("/ListOrder")
	public String doGetListOrder(Model model) {
		// ** Code Here
		// ...

		return "admin/ListOrder";
	}

	@PostMapping("/product/productMgt/insert")
	public ResponseEntity<?> insertProd(@RequestBody productDto newProd) {
		Message msg = new Message();
		try {
			Products prod = new Products();
			if (newProd.getId() != 0) {
				prod.setId(newProd.getId());
			}
			prod.setName(newProd.getName());
			prod.setPrice(newProd.getPrice());
			prod.setQuantity(newProd.getQuantity());
			prod.setTypeOfProduct(typeServices.findById(newProd.getTypeof()));
			prod.setUnitType(unitServices.findById(newProd.getUnitof()));
			prod.setImgUrl(newProd.getImgUrl());
			prod.setDescription(newProd.getDescription());
			prod.setIsDeleted(1.0);
			if (newProd.getSlug() != "") {
				prod.setSlug(newProd.getSlug());
				msg.setStatus("Update successfully !!!");
			} else {
				prod.setSlug("Please update slug");
				msg.setStatus("Insert successfully !!!");
			}
			prodServices.SaveOrUpdate(prod);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("Failure !!!");
		}
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/product/productMgt/delete")
	public ResponseEntity<?> delete(@RequestBody productDto deleteProd) {
		Message msg = new Message();
		try {
			Products prod = new Products();
			prod.setId(deleteProd.getId());
			prod.setName(deleteProd.getName());
			prod.setPrice(deleteProd.getPrice());
			prod.setQuantity(deleteProd.getQuantity());
			prod.setTypeOfProduct(typeServices.findById(deleteProd.getTypeof()));
			prod.setUnitType(unitServices.findById(deleteProd.getUnitof()));
			prod.setImgUrl(deleteProd.getImgUrl());
			prod.setDescription(deleteProd.getDescription());
			prod.setIsDeleted(0.0);
			prod.setSlug(deleteProd.getSlug());
			prodServices.SaveOrUpdate(prod);
			msg.setStatus("Delete successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("Failure !!!");
		}
		return ResponseEntity.ok(msg);
	}
}
