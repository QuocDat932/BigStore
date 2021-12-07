package codejava.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import codejava.Constant.Utility;
import codejava.Entity.Account;
import codejava.Entity.Users;
import codejava.Entity.roles;
import codejava.Services.AccountService;
import codejava.Services.ProductsServices;
import codejava.Services.RolesServices;
import codejava.Services.UserServices;
import net.bytebuddy.utility.RandomString;

@Controller
public class RegisterController {
	@Autowired
	private RolesServices rolesservices;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserServices userservices;
	@Autowired
	private ProductsServices productsservices;
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	@Autowired
	JavaMailSender mailSender;
	
	@GetMapping("/home/register")
	public String doGetRegister(Model model, Model model2) {
		// Users user = new
		// Users(0,"Fullname","Username","hashPassword","email","ImgUrl",null,null);
		model.addAttribute("newAccount", new Account());
		model2.addAttribute("newUser", new Users());
		return "home/register";
	}

	@PostMapping("register")
	public String doPostRegistration(Model model, @ModelAttribute("newAccount") @Validated Account newAccount,
			@ModelAttribute("newUser") @Validated Users newUser, HttpServletRequest request) {

		try {
			String token = RandomString.make(30); 
			newUser.setImgUrl("");
			roles role = rolesservices.findByID(2);
			newUser.setRole(role);
			newUser.setEmail(newUser.getEmail());
			newUser.setType_account("SYS");
			newUser.setIsDeleted(false);
			newUser.setResetpasswordtoken(token);
			userservices.addUser(newUser);
			
			newAccount.setUsername(newAccount.getUsername());
			newAccount.setHashPassword(bcrypt.encode(newAccount.getHashPassword())); 
			newAccount.setUsers(userservices.findByEmail(newUser.getEmail()));
			accountService.addAccount(newAccount);
			
			//Send Email
			String email = newUser.getEmail();
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/home/CofrimRegister?token=" + token;
			sendEmail(email, resetPasswordLink);
			
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			// return "redirect:/home/register";
			return "12344";
		}

	}
	
	@GetMapping("/home/CofrimRegister")
	public String dogetCofrimRegister(@Param(value = "token") String token, Model model) {
		  Users users = userservices.findByTokenPassword(token);
		    model.addAttribute("token", token);
		    if (users == null) {
		        model.addAttribute("message", "Invalid Token");
		        return "message";
		    }
		return "home/CofrimRegister";
	}
	
	@PostMapping("/home/CofrimRegister")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    Users users = userservices.findByTokenPassword(token);
	    Account account = accountService.findByUsers_Id(users.getId());
	    if (users == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    } else {           
	    	users.setIsDeleted(true);
	    	users.setResetpasswordtoken(null);
	    	userservices.addUser(users);
	        return "redirect:/home";
	    }
	     
	}
	
	
	public void sendEmail(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("Bigstore@shopme.com", "Big Store Support");
	    helper.setTo(recipientEmail);
	     
	    String subject = "Here's the link to Cofrim your account";
	     
	    String content = "<p>Hello,</p>"
	            + "<p>Thanks you have registered for my website.</p>"
	            + "<p>Click the link below to fisnish register:</p>"
	            + "<p><a href=\"" + link + "\">Cofrim your account</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you don't want register "
	            + "or you have not made the request.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
}
