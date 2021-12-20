package codejava.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import codejava.Dto.Message;
import codejava.Dto.usersDto;
import codejava.Entity.Users;


@Controller
public class ContactController {
	@Autowired
	JavaMailSender mailSender;
	
	
	@GetMapping("/home/contact")
	public String doGetContact() {
		return "home/contact";
	}
	
	@PostMapping("/home/sendEmailUsers")
	ResponseEntity<?>  SendEmailUsers(HttpServletRequest request, @RequestBody usersDto newUser )throws Exception{
		Message msg = new Message();
	    String subject = "Feedback của khách hàng"; 
	    String email = "bigstoreFeedback@gmail.com";	
		String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	    
//	    String username = request.getParameter("username");	
//	    String phone = request.getParameter("phone");	
//	    String content = request.getParameter("content");	
//	    if(newUser.getUsername() ==null || newUser.getContent()==null) {
//	    	msg.setStatus("Hãy Điền Đủ Thông Tin")s;
//	    	return ResponseEntity.ok(msg);
//	    }
//		if(!newUser.getPhone().matches(pattern)) {
//			msg.setStatus("Sai Định Dạng Phone");
//	    	return ResponseEntity.ok(msg);
//		}
	    String username = newUser.getUsername();	
	    String phone = newUser.getPhone();	
	    String content = newUser.getContent();	
	    System.out.println(""+username);
	    
	    sendEmail( email,  username,  subject,phone,content );
	    msg.setStatus("Đã Gửi Mail Cho Big Store !!!");
		return ResponseEntity.ok(msg);
	}
	
	public void sendEmail(String email, String username, String subject,String phone,String contact) 
	        throws MessagingException, UnsupportedEncodingException {
		
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("Bigstore@shopme.com", "Big Store Support");
	    helper.setTo(email);

//	    String content = "<p>Hello,</p>"
//	            + "<p>You have requested to reset your password.</p>"
//	            + "<p>Click the link below to change your password:</p>"
//	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
//	            + "<br>"
//	            + "<p>Ignore this email if you do remember your password, "
//	            + "or you have not made the request.</p>";
//	    
	    String contactfull = "<p>Hello,</p>" 
                +"<p>My Name is "+username+" and my phone is: "+ phone+ " </p>"
                + "<br>"
                + "<p>I need support is: "+contact+" Thanks and please help me </p>";
	    helper.setSubject(subject);
	    helper.setText(contactfull, true);
	    mailSender.send(message);
	}
}
