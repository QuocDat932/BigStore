package codejava.API.APIADM;

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
	@GetMapping("/indexUser")
	public ResponseEntity<?> doGetIndexUser(@RequestParam("userId") int userId){
		Users userIndex = userSrvs.findByid(userId);
		return ResponseEntity.ok(userIndex);
	};
	@GetMapping("/totalUser")
	public ResponseEntity<?> doGetTotalUser(){
		String[][] dataUs = adminSrvs.getcountUs();
		System.out.println(dataUs[0]);
		return ResponseEntity.ok(dataUs);
	};
	@GetMapping("totalPricebyId")
	public ResponseEntity<?> doGetTotalPricebyId(@RequestParam("userId") int userId){
		String[][] dataPrice = adminSrvs.getTotalPriceByUser(userId);
		for(int i=0; i< 6;i++) {
			System.out.println("userId: "+ userId);
			System.out.println("Title : "+ dataPrice[0][5-i]);
			System.out.println("Value : "+ dataPrice[1][5-i]);
		}
		return ResponseEntity.ok(dataPrice);
	}
}
