package codejava.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import codejava.Constant.SessionConst;
import codejava.Dto.Message;
import codejava.Dto.usersDto;
import codejava.Entity.Account;
import codejava.Entity.Users;
import codejava.Services.AccountService;
import codejava.Services.UserServices;

@Controller
public class AccountController {
	@Autowired
	UserServices userServ;
	
	@Autowired
	AccountService accountServ;
	
	
	@GetMapping("/home/account")
	public String doGetAccount(Model model,HttpSession sess) {
//		Users u = (Users) sess.getAttribute(SessionConst.CURRENT_USER);
//		Users users = userServ.findByid(u.getId());
//		Account account = accountServ.findByUsers_Id(u.getId());
//		model.addAttribute("Users", users);
//		model.addAttribute("Account", account);
		return "home/profile";
	}
	
}
