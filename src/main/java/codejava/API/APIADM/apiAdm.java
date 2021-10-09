package codejava.API.APIADM;

import codejava.Entity.Products;
import codejava.Services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Users;
import codejava.Services.StatsServices;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/admin")
public class apiAdm {
	@Autowired
	private UserServices userSrvs;
	@Autowired
	private StatsServices adminSrvs;
	@Autowired
	private ProductsServices prodServices;
	@GetMapping("/indexUser")
	public ResponseEntity<?> doGetIndexUser(@RequestParam("userId") int userId){
		Users userIndex = userSrvs.findByid(userId);
		return ResponseEntity.ok(userIndex);
	};
	@GetMapping("/totalUser")
	public ResponseEntity<?> doGetTotalUser(){
		String[][] dataUs = adminSrvs.getcountUs();
		return ResponseEntity.ok(dataUs);
	};
	@GetMapping("totalPricebyId")
	public ResponseEntity<?> doGetTotalPricebyId(@RequestParam("userId") int userId){
		String[][] dataPrice = adminSrvs.getTotalPriceByUser(userId);
		return ResponseEntity.ok(dataPrice);
	}
	@GetMapping("/indexProd")
	public ResponseEntity<?> getIndexProd(@RequestParam("index") int index){
		Products prod = prodServices.findById(index);
		return ResponseEntity.ok(prod);
	}
	@GetMapping("/allProd")
	public ResponseEntity<?> getAllProd(){
		return ResponseEntity.ok(prodServices.findAll());
	}
}
