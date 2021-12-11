package codejava.Controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.bind.DatatypeConverter;

import codejava.Dto.Message;
import codejava.Dto.productDto;
import codejava.Dto.usersDto;
import codejava.Entity.*;
import codejava.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import codejava.Constant.RoleConst;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Constant.publicFuncs;
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
	@Autowired
	private RolesServices roleServices;

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	@GetMapping("/login")
	public String doGetLogin(Model model, HttpSession session) {
		System.out.println("Admin Login");
		model.addAttribute("user", new Account());
		model.addAttribute("message", "Login to continue");
		return "admin/login";
	}

	@PostMapping("/login")
	public String doPostLogin(Model model, @ModelAttribute("user") @Validated Account accountLogin,
			HttpSession session) {
		try {
			UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(
					accountLogin.getUsername(), accountLogin.getHashPassword());
			Authentication authentication = authenManager.authenticate(authenInfo);
			CustomUser customUser = (CustomUser) authentication.getPrincipal();
			
			Account userAccount = accountServices.findByUsername(accountLogin.getUsername());
			Users userResponse = userAccount.getUsers();
			roles RoleUserResponse = userAccount.getUsers().getRole();

			if ( userAccount.getUsers().getIsDeleted() && RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_ADMIN)
					|| RoleUserResponse.getDescription().equalsIgnoreCase(RoleConst.ROLE_MANAGER)) {
				session.setAttribute(SessionConst.CURRENT_ADMIN, userResponse);
				session.setAttribute(SessionConst.CURRENT_ROLE, RoleUserResponse);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return "redirect:/admin/dashboard";
			}
			else {
				model.addAttribute("message", "You are not allow");
				SecurityContextHolder.getContext().setAuthentication(authentication);
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
		model.addAttribute("message", "Login to continue");
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

	@PostMapping("/users/userMgt/insert")
	public ResponseEntity<?> insertUser(@RequestBody usersDto newUser) throws IOException {
		Message msg = new Message();
		// tạo slug ảnh
		try {
		String input = newUser.getUsername();
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		
		System.out.println("hinh:" + newUser.getImgUrl());
		// tạo base64
		String extension = "jpg";
		if (newUser.getImgUrl() != null) {
			String base64String = newUser.getImgUrl();
			String[] strings = base64String.split(",");

			// convert base64 string to binary data
			Path path1 = Paths.get("src/main/resources/static/admin/images/faces/");
			System.out.println("string:" + strings.length);
			if (strings.length > 1) {

				switch (strings[0]) {// check image's extension
				case "data:image/jpeg;base64":
					extension = "jpg";
					break;
				case "data:image/png;base64":
					extension = "png";
					break;
				case "data:image/jpg;base64":
					extension = "jpg";
					break;
				default:// should write cases for more images types
					extension = "false";
					break;
				}

				// đổi và add hình vào

				if (extension == "false") {
					msg.setStatus("IMG must type PNG, JPG");
					return ResponseEntity.ok(msg);
				}
				byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
				String path = path1 + "//" + slug.toLowerCase(Locale.ENGLISH) + "." + extension;
				File file = new File(path);

				try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
					outputStream.write(data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			Users users = new Users();
			Account account = new Account();
			if (newUser.getId() != 0) {
				users.setId(newUser.getId());
			} else {
				if (accountServices.findByUsername(newUser.getUsername()) == null) {
					account.setUsername(newUser.getUsername());
				} else {
					msg.setStatus("Has Username");
					return ResponseEntity.ok(msg);
				}
				account.setHashPassword(bcrypt.encode(newUser.getHashPassword()));
			}
			Users checkuseremail = userservices.findByEmail(newUser.getEmail());
			users.setFullname(newUser.getFullname());
			if (checkuseremail != null) {
				if (!newUser.getEmail().equalsIgnoreCase(checkuseremail.getEmail())) {
					msg.setStatus("Has Email");
					return ResponseEntity.ok(msg);
				}
			}
			if (!newUser.getEmail().matches(EMAIL_PATTERN)) {
				msg.setStatus("Wrong Format Email");
				return ResponseEntity.ok(msg);

			} else {
				users.setEmail(newUser.getEmail());
			}
			users.setIsDeleted(newUser.getIsDeleted());
			users.setRole(roleServices.findByID(2));
			users.setPhone(newUser.getPhone());
			users.setAddress(newUser.getAddress());
			System.out.println("user"+newUser.getImgUrl());
			if (newUser.getImgUrl() != null) {
				users.setImgUrl(slug.toLowerCase(Locale.ENGLISH) + "." + extension);
			} else {
				users.setImgUrl("null" + "." + extension);
			}
			users.setRole(roleServices.findByID(newUser.getRoleId()));
			users.setType_account("SYS");
			// Create Users
			userservices.SaveAndUpdate(users);
			// Create Account
			account.setUsers(userservices.findByEmail(newUser.getEmail()));
			if (newUser.getId() == 0) {
				accountServices.addAccount(account);
			}
			msg.setStatus("Successfully !!!");

		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("Failure !!!");
		}
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/users/userMgt/delete")
	public ResponseEntity<?> deleteuser(@RequestBody usersDto newUser) {
		Message msg = new Message();
		try {
			Users users = userservices.findByEmail(newUser.getEmail());
			userservices.findByEmail(newUser.getEmail());
			users.setIsDeleted(false);
			userservices.addUser(users);
			msg.setStatus("Delete successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("Failure !!!");
		}
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/product/productMgt/insert")
	public ResponseEntity<?> insertProd(@RequestBody productDto newProd) throws IOException {
		Message msg = new Message();
		// tạo slug
		String input = newProd.getName();
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		System.out.println("hinh:" + newProd.getImgUrl());
		// tạo base64
		String base64String = newProd.getImgUrl();
		String[] strings = base64String.split(",");
		String extension = "jpg";

		// convert base64 string to binary data
		Path path1 = Paths.get("src/main/resources/static/home/images/");
		System.out.println("string:" + strings.length);
		if (strings.length > 1) {
			switch (strings[0]) {// check image's extension
			case "data:image/jpeg;base64":
				extension = "jpg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			case "data:image/jpg;base64":
				extension = "jpg";
				break;
			}
			if (extension == "false") {
				msg.setStatus("IMG must type PNG, JPG");
				return ResponseEntity.ok(msg);
			}
			// đổi và add hình vào
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			String path = path1 + "//" + slug.toLowerCase(Locale.ENGLISH) + "." + extension;
			File file = new File(path);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// thêm product
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

			prod.setDescription(newProd.getDescription());
			prod.setIsdeleted(1);
			prod.setSlug(slug.toLowerCase(Locale.ENGLISH));
			if (strings.length > 1) {
				prod.setImgUrl(slug.toLowerCase(Locale.ENGLISH) + "." + extension);
			} else {
				prod.setImgUrl(newProd.getSlug() + "." + extension);
			}
			// convert Date to Local Time
			int datex =  newProd.getToUsingDate().getDate();
		    newProd.getToUsingDate().setDate(datex);
			prod.setFrmdate(publicFuncs.convertToLocalDateTimeViaMilisecond(newProd.getFrmUsingDate()));
			prod.setTodate(publicFuncs.convertToLocalDateTimeViaMilisecond(newProd.getToUsingDate()));
			prodServices.SaveOrUpdate(prod);
			msg.setStatus("Success");
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
			prod.setIsdeleted(0);
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
