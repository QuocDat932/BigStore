package codejava.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import codejava.Constant.Utility;
import codejava.Entity.Account;
import codejava.Entity.Users;
import codejava.Services.AccountGGService;
import codejava.Services.AccountService;
import codejava.Services.UserServices;
import net.bytebuddy.utility.RandomString;

@Controller
public class ForgetPasswordController {
	@Autowired
	UserServices userService; 
	@Autowired
	AccountService accountService; 
	@Autowired
	AccountGGService accountGGService; 
	@Autowired
	JavaMailSender mailSender;
	
	@GetMapping("/home/CofrimPassword")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    Users users = userService.findByTokenPassword(token);
	    System.out.println("Users" + users.getFullname());
	    model.addAttribute("token", token);
	    if (users == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    }
		return "home/CofrimPassword";
	}
	
	
	@PostMapping("/home/CofrimPassword")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    String password = request.getParameter("password");
	    Users users = userService.findByTokenPassword(token);
	    Account account = accountService.findByUsers_Id(users.getId());
	    model.addAttribute("title", "Reset your password");
	    if (users == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "message";
	    } else {           
	    	updatePassword(account, users,password);
	        model.addAttribute("message", "You have successfully changed your password.");
	        return "redirect:/home";
	    }
	     
	}
	
	
	
    public boolean updateResetPasswordToken(String token, String email)  {
        Users users = userService.findByEmail(email);
        if (users != null) {
            users.setResetpasswordtoken(token);
            userService.SaveAndUpdate(users);
            return true;
        } else {
            return false;
            
        }
		
    }
    @GetMapping("/home/sendEmail")
    public String showForgotPasswordForm() {
        return "home/forgetPassword(Email)";
    }
    
    
	@PostMapping("/home/sendEmail")
	public String doGetEmail(HttpServletRequest request, Model model) throws Exception {
		String email = request.getParameter("email");
	    String token = RandomString.make(30);
	    System.out.println("email " +email);
	    
	     if(accountGGService.findByEmail(email) != null) {
	    	 model.addAttribute("error", "Không được Update Tài khoản này");
	    	 model.addAttribute("message", "We haven't sent a reset password link to your email. Please check.");
	    	 return "home/forgetPassword(Email)";
	     }
	     else {
	    try {
	       if(updateResetPasswordToken(token, email)==false) {
	    	   model.addAttribute("error", "Don't have account in BigStore");
	    	   return "home/forgetPassword(Email)";
	       };
	       
	       
	        String resetPasswordLink = Utility.getSiteURL(request) + "/home/CofrimPassword?token=" + token;
	        System.out.println("Token:"+ resetPasswordLink);
	        sendEmail(email, resetPasswordLink);
	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
	    } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }}
	         
		return "home/forgetPassword(Email)";
	}
	 public void updatePassword(Account account,Users users, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        account.setHashPassword(encodedPassword);
	         
	        users.setResetpasswordtoken(null);
	        accountService.addAccount(account);
	        userService.addUser(users);
	    }
	public void sendEmail(String recipientEmail, String link)
	        throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("Bigstore@shopme.com", "Big Store Support");
	    helper.setTo(recipientEmail);
	     
	    String subject = "Here's the link to reset your password";
	     
	    String content = "<p>Hello,</p>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the link below to change your password:</p>"
	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
}
