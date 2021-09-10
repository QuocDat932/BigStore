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
public class a {
	@Autowired
	private UserServices repo;
	
	@GetMapping({"test"})
	public String doGetController(Model model) {
		
		return "layout/test";
	};
	
	
}
