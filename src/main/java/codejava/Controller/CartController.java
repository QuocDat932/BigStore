package codejava.Controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Constant.publicVariable;
import codejava.Dto.cartDetailDto;
import codejava.Dto.cartDto;
import codejava.Dto.productDto;
import codejava.Entity.OrderDetails;
import codejava.Entity.Orders;
import codejava.Entity.Products;
import codejava.Entity.Users;
import codejava.Services.OrderDetailServices;
import codejava.Services.Orderservices;
import codejava.Services.ProcessService;
import codejava.Services.ProductsServices;
import codejava.Constant.publicConst;

@Controller
@RequestMapping("home/")
public class CartController {
	@Autowired
	private Orderservices OrderS;
	@Autowired
	private OrderDetailServices OrderDlS;
	@Autowired
	private ProductsServices ProductS;
	@Autowired
	private ProcessService ProcessS;
	
	
	@GetMapping("/wishlist")
	public String doGetWishlistHttp(HttpSession session) {
////		List<cartDto> cart = (List<cartDto>) session.getAttribute(SessionConst.CURRENT_CART);
//		cart.forEach(c-> {
//			System.out.println(c.getUserId());
//		});		
		return "home/wishlist";
	}
	@GetMapping(value = "/cart")
	public String doPostCart(Model model) {
		return "home/cart";
	}
	

	@PostMapping(value = "/cart/save")
	public String doPostSaveCart(Model model, HttpSession sess) {
		Double totalPrice = 0.0;
		if (Objects.isNull(publicVariable.ListCart) || publicVariable.ListCart.size() == 0) {
			return "redirect:/home";
		}
		if (Objects.isNull(sess.getAttribute(SessionConst.CURRENT_USER))
				|| sess.getAttribute(SessionConst.CURRENT_USER) == "") {
			return "redirect:/home/login";
		}
		Users u = (Users) sess.getAttribute(SessionConst.CURRENT_USER);
		Orders o = new Orders();
		o.setUser(u);
		o.setPhone("0982769574");
		o.setAddress("DongNai");
		o.setProcess(ProcessS.findBySlug(publicConst.Orderprocess.NEW));
		
		for(productDto dt : publicVariable.ListCart) {
			totalPrice += (dt.getQuantity() * dt.getPrice());
		};
		o.setTotalprice(totalPrice);
		
		try {
			OrderS.insert(o); // Insert Order
			o = OrderS.findNewOrder(u);
			System.out.println("Create Order Done : ID : "+o.getId());
			if (Objects.isNull(o)) {
				System.out.println("Error CartController");
				return "redirect:/home";
			}else {
				
			}
		} catch (Exception e) {
			System.out.println("#1 -" + e);
			return "redirect:/home";
		}
		final int idO = o.getId();
		int z = 0;
//		for (productDto c : publicVariable.ListCart) {
//			
//		}
		publicVariable.ListCart.forEach(c -> {
			try {
				int quantity = c.getQuantity();
				Products product = ProductS.findById(c.getId());
				Double price = product.getPrice();
				cartDetailDto OrdDtl = new cartDetailDto(price, c.getQuantity(), idO, c.getId());
				System.out.println("-----------------");
				System.out.println("idProd   >> "+c.getId());
				System.out.println("IdOrd    >> "+OrdDtl.getIdOrder());
				System.out.println("Quantity >> "+OrdDtl.getQuantity());
				System.out.println("Price    >> "+OrdDtl.getPrice());
				OrderDlS.save(OrdDtl);
				System.out.println("ORDER DETAIL SAVE DONE !");
			} catch (Exception e) {
				System.out.println("ERROR insert cart detail - " + e);
			}
		});
		return "home/cart";
	}
}
