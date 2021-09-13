package codejava.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/admin")
@Controller(value = "AdminController")
public class AdminController {
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
