package codejava.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codejava.Constant.publicConst;
import codejava.Dto.ListproductDto;
import codejava.Dto.productDto;
import codejava.Entity.Products;
import codejava.Services.ProductsServices;

@Controller
@RequestMapping("/api/user/controller")
public class ReplaceController {
		@Autowired
		private ProductsServices productSrvs;
		@GetMapping("/top4product")
		public String goGettop4product(@RequestParam("param") String param, HttpSession session){
			List<Products> top4 = productSrvs.findtop4Bytype(param);
			ListproductDto listtop = (ListproductDto) session.getAttribute(publicConst.Top4Prod);
			listtop.listTop4 = top4;
			return "home/index::#viewTop4prod";
	}
}
