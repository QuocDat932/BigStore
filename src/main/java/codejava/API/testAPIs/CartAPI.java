package codejava.API.testAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.MessageAPI;
import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Constant.publicVariable;
import codejava.Dto.cartDetailDto;
import codejava.Dto.cartDto;
import codejava.Dto.productDto;
import codejava.Entity.Order_Process;
import codejava.Entity.Orders;
import codejava.Entity.PaymentMethod;
import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Entity.Users;
import codejava.Responsitory.PaymentRepo;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.CartService;
import codejava.Services.OrderDetailServices;
import codejava.Services.Orderservices;
import codejava.Services.PaymentService;
import codejava.Services.ProcessService;
import codejava.Services.ProductsServices;
import codejava.Services.Order_ProcessServices;
@RestController
@RequestMapping("api/cart")
public class CartAPI {

	@Autowired
	private CartService cart;
	@Autowired
	private Orderservices OrderS;
	@Autowired
	private OrderDetailServices OrderDlS;
	@Autowired
	private ProductsServices ProductS;
	@Autowired
	private ProcessService ProcessS;
	@Autowired
	private PaymentService PaymentS;
	@Autowired
	private Order_ProcessServices Order_ProS;
//	@GetMapping("/update")
//	public ResponseEntity<?> dogetUpdateCart(@RequestParam("product") Integer idProduct,
//			@RequestParam("quantity") Integer quantity, @RequestParam("isUpdate") Boolean isUpdate,
//			HttpSession session) {
//		cartDto currentCart = (cartDto) session.getAttribute(SessionConst.CURRENT_CART);
//		currentCart = cart.updateProduct(currentCart, idProduct, quantity, isUpdate);
//		// System.out.println(currentCart.getListDetail().get(4).getName());
//		return ResponseEntity.ok(currentCart);
//	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> doPostSaveCart(HttpSession sess,
			@RequestParam Optional<String> phone,
			@RequestParam Optional<String> address,
			@RequestParam Optional<String> discription,
			@RequestParam Optional<Integer> payment
			) {
		if (Objects.isNull(publicVariable.ListCart) || publicVariable.ListCart.size() == 0) {
			return ResponseEntity.ok(MessageAPI.message("Failed", "Missing Cart", null));
		}
		if (Objects.isNull(sess.getAttribute(SessionConst.CURRENT_USER))
				|| sess.getAttribute(SessionConst.CURRENT_USER) == "") {
			return ResponseEntity.ok(MessageAPI.message("Failed", "Missing User", null));
		}
		if (Objects.isNull(phone) || Objects.isNull(address)) {
			return ResponseEntity.ok(MessageAPI.message("Failed", "Missing Address or Phone", null));
		}
		
		PaymentMethod pay = PaymentS.findById(payment.orElse(1));
		Users u = (Users) sess.getAttribute(SessionConst.CURRENT_USER);
		Orders o = new Orders();
		o.setUser(u);
		o.setPhone(phone.get());
		o.setOrderdescription(discription.orElse("Nothing"));
		o.setAddress(address.get());
		o.setPaymentmethod(pay);
		o.setPaymentsts("N");
		Double pricezz = 0D;
		for (productDto c : publicVariable.ListCart) {
			pricezz+=(c.getPrice()*c.getQuantity());
		}
		o.setTotalprice(pricezz);
		o.setProcess(ProcessS.findBySlug(publicConst.Orderprocess.NEW));
		try {
			OrderS.insert(o); // Insert Order
			o = OrderS.findNewOrder(u);
			System.out.println("Create Order Done : ID : " + o.getId());
			if (Objects.isNull(o)) {
				System.out.println("Error CartController");
				return ResponseEntity.ok(MessageAPI.message("Failed", "ERROR for create Order 1", null));
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.ok(MessageAPI.message("Failed", "ERROR for create Order 2", null));
		}
		//Save List Order Details
		final int idO = o.getId();
		boolean check = true;
		List<productDto> listdto = publicVariable.ListCart;
		for (int i = 0; i < listdto.size(); i++) {
			
			try {
				productDto c = listdto.get(i);
				int quantity = c.getQuantity();
				Products product = ProductS.findById(c.getId());
				Double pricee = product.getPrice();
				cartDetailDto OrdDtl = new cartDetailDto(pricee, c.getQuantity(), idO, c.getId());
				System.out.println("-----------------");
				System.out.println("idProd   >> " + c.getId());
				System.out.println("IdOrd    >> " + OrdDtl.getIdOrder());
				System.out.println("Quantity >> " + OrdDtl.getQuantity());
				System.out.println("Price    >> " + OrdDtl.getPrice());
				OrderDlS.save(OrdDtl);
				System.out.println("ORDER DETAIL SAVE DONE !");
			} catch (Exception e) {
				System.out.println("ERROR insert cart detail - " + e);
				check=false;
			}
		}
		if(!check) {
			return ResponseEntity.ok(MessageAPI.message("Failed", "ERROR for create Order detail", null));
		}
		//Save Order Process
		try {
			Order_Process op = new Order_Process();
			op.setOrder(o);
			op.setProcessStep(ProcessS.findBySlug(publicConst.Orderprocess.NEW));
			op.setUserProcess(u);
			Order_ProS.Save(op);
		} catch (Exception e) {
			return ResponseEntity.ok(MessageAPI.message("Failed", "ERROR for create Order Process", null));
		}
		Map<String, Object> result = new  HashMap<>();
		result.put("order", o);
		result.put("order", listdto);
		return ResponseEntity.ok(MessageAPI.message("Submited", "Everything is Done", result));
	}

}
