package codejava.API.APIADM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.OrderDetails;
import codejava.Entity.Orders;
import codejava.Services.OrderDetailServices;
import codejava.Services.Order_ProcessServices;
import codejava.Services.Orderservices;

@RestController
@RequestMapping("/api/admin")
public class apiAdmOrder {
	@Autowired
	private Orderservices OrdServs;
	
	@Autowired
	private OrderDetailServices ordDtlServs;
	
	@Autowired
	private Order_ProcessServices ordPrcssServs;
	@GetMapping("/order/getLstOrder")
	public ResponseEntity<?> doGetLstOrder(){
		List<Orders> result = OrdServs.findAll();
		return ResponseEntity.ok(result);
	};
	@GetMapping("/order/getLstOrderDtl")
	public ResponseEntity<?> doGetLstOrderDtl(@RequestParam("ordId") int ordId) throws Exception{
		List<OrderDetails> result;
		try {
			result = ordDtlServs.listOrdDtlById(ordId);
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return ResponseEntity.ok(result);
	};
	@GetMapping("/order/getOrder_Process")
	public ResponseEntity<?> doGetOrder_Process(@RequestParam("ordId") int ordId) throws Exception{
		return ResponseEntity.ok(ordPrcssServs.lstOrder_ProcessByOrdId(ordId));
	}
}
