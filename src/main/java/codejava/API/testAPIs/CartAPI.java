package codejava.API.testAPIs;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.SessionConst;
import codejava.Dto.cartDto;

import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.CartService;
import codejava.Services.ProductsServices;
import codejava.Services.TypeOfProductServices;

@RestController
@RequestMapping("cart/api")
public class CartAPI {
	
@Autowired
private CartService cart;





@GetMapping("/update")
public ResponseEntity<?> dogetUpdateCart(
	@RequestParam("product") Integer idProduct ,
	@RequestParam("quantity")Integer quantity,
	@RequestParam("isUpdate") Boolean isUpdate,
	HttpSession session)	{
	cartDto currentCart = (cartDto) session.getAttribute(SessionConst.CURRENT_CART);
	currentCart = cart.updateProduct(currentCart, idProduct, quantity, isUpdate);
	//System.out.println(currentCart.getListDetail().get(4).getName());
	return ResponseEntity.ok(currentCart);
	
}


}
