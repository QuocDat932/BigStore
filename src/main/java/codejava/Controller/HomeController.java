package codejava.Controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codejava.Entity.userObj;
import codejava.Services.UserServices;

@Controller
public class HomeController {
	@Autowired
	private UserServices repo;
	
	@GetMapping({"/home","/"})
	public String doGetController(Model model) {
		
		return "home/index";
	};
	@GetMapping("/home/login")
	public String doGetLogin(Model model) {
		model.addAttribute("user", new userObj(0,"username","PassWord",0));
		return "home/login";
	};
	@PostMapping("/home/login")
	public String doPostLogin(Model model, @ModelAttribute("user") userObj user) {
		System.out.println("UserName : "+user.getUsername());
		System.out.println("Password : "+user.getPassword());
		return "redirect:/home";
	}
	@GetMapping("/remove")
	public String doGetRemove(Model model, @RequestParam("id") int userid) {
	//public String doGetRemove(Model model, @PathVariable("id") int userid) {
		System.out.println(userid);
		return "home/index";
	}
	
	@GetMapping("/home/codes")
	public String doGetCodes() {
		return "home/codes";
	}
	
}
