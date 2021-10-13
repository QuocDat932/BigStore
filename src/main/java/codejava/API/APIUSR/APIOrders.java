package codejava.API.APIUSR;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Orders;
import codejava.Entity.Users;
import codejava.Services.Orderservices;

@RestController
@RequestMapping("/api/order")
public class APIOrders {
	@Autowired 
	private Orderservices orderServ;
	@GetMapping("/orderTable")
	public ResponseEntity<?> doGetOrderTable(@RequestParam("TYPE") String type,
											 @RequestParam("paramInt1") int paramInt1,
											 @RequestParam("paramString1") String paramString1,
											 HttpSession session){
		if(session.getAttribute("currentUser") == null) {
			return ResponseEntity.ok(null);
		}else {
			Users userCurrent = (Users) session.getAttribute("currentUser");
			List<Orders> result = orderServ.findByParam(userCurrent.getId(), type, paramInt1, paramString1);		
			return ResponseEntity.ok(result);
		}
	}
}
