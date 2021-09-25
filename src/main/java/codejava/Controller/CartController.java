package codejava.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import codejava.Constant.SessionConst;
import codejava.Dto.cartDto;
@Controller
@RequestMapping("home/")
public class CartController {
	
	@GetMapping("/wishlist")
	public String doGetWishlistHttp(HttpSession session) {
		List<cartDto> cart = (List<cartDto>) session.getAttribute(SessionConst.CURRENT_CART);
		cart.forEach(c-> {
			System.out.println(c.getUserId());
		});		
		return "home/wishlist";
	}
	
}
