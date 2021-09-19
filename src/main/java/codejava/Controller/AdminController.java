package codejava.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codejava.Constant.publicConst;
import codejava.Entity.Users;
import codejava.Services.StatsServices;
import codejava.Services.UserServices;
import codejava.Constant.publicConst;

@RequestMapping("/admin")
@Controller(value = "AdminController")
public class AdminController {
	@Autowired
	private StatsServices adminSrvs;
	@Autowired
	private UserServices userSrvs;
	
	@GetMapping({"/home","/"})
	public String doGetIndexAdmin(Model model) {
		//** Code Here
		//...
		
		return "layout/indexAdmin";
	};
	@GetMapping("/dashboard")
	public String doGetdashboard(Model model) {
		//** Code Here
		//...
		
		return "admin/index";
	};
	@GetMapping("/forms")
	public String doGetForm(Model model) {
		//** Code Here
		//...
		
		return "admin/basic-forms";
	};
	@GetMapping("/product/productMgt")
	public String doGetProduct(Model model) {
		//** Code Here
		//...
		
		return "admin/Product-productMgt";
	};
	@GetMapping("/product/categotyMgt")
	public String doGetCategory(Model model) {
		//** Code Here
		//...
		
		return "admin/Product-categoryMgt";
	};
	@GetMapping("/user/user-userMgt")
	public String doGetUser(Model model) {
		//** Code Here
		//...
		String[][] dataUs = adminSrvs.getcountUs();
		List<Users> users = userSrvs.findAll();
		model.addAttribute("dataUs", dataUs);
		model.addAttribute("users", users);
		System.out.println(">> NEW : "+ publicConst.Orderprocess.APPROVE);
		return "admin/User-userMgt";
	};
	@GetMapping("/user/user-userVipMgt")
	public String doGetUserVip(Model model) {
		//** Code Here
		//...
		return "admin/User-userVipMgt";
	};
	@GetMapping("/ChartOrder")
	public String doGetChartOrder(Model model) {
		//** Code Here
		//...
				
		return "admin/ChartOrder";
	};
	@GetMapping("/ListOrder")
	public String doGetListOrder(Model model) {
		//** Code Here
		//...
						
		return "admin/ListOrder";
	}
	
}
