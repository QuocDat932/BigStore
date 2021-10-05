package codejava.API.testAPIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.publicConst;
import codejava.Constant.publicVariable;
import codejava.Dto.productDto;
import codejava.Entity.Orders;
import codejava.Entity.OrdersProcess;
import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.OrderProcessServices;
import codejava.Services.Orderservices;
import codejava.Services.ProductsServices;
import codejava.Services.TypeOfProductServices;

@RestController
@RequestMapping("/api")
public class testOrder {

	@Autowired
	private OrderProcessServices OPS;
	@Autowired
	private Orderservices OS;
	
	@GetMapping("/orders")
	public  ResponseEntity<?> getOrders(){
		return ResponseEntity.ok(OS.findAll());
	}
	@GetMapping("/order/{id}")
	public  ResponseEntity<?> getOrdersId(@PathVariable int id){
		return ResponseEntity.ok(OS.findById(id));
	}
	@GetMapping("/order/{id}/OrderProcess")
	public  ResponseEntity<?> getOrdersIdOrderProcess(@PathVariable int id){
		Orders o = OS.findById(id);
		List<OrdersProcess> op = OPS.findByOrder(o);
		return ResponseEntity.ok(op);
	}
	@GetMapping("/ordersProcess")
	public  ResponseEntity<?> getOrderProcess(){
		return ResponseEntity.ok(OPS.findAll());
	}
	@GetMapping("/ordersProcess/{id}")
	public  ResponseEntity<?> getOrderProcessId(@PathVariable int id){
		return ResponseEntity.ok(OPS.findById(id));
	}
}
