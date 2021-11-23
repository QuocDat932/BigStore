package codejava.API.APIADM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Users;
import codejava.Services.StatsServices;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/admin")
public class apiADMUserMgt {
	@Autowired
	private StatsServices adminSrvs;
	@Autowired
	private UserServices userSrvs;
	@GetMapping("/userMgt/ReloadDataUserChart")
	public ResponseEntity<?> doGetReloadDataUser(){
		String[][] dataUs = adminSrvs.getcountUs();
		return ResponseEntity.ok(dataUs);
	};
	
	@GetMapping("/userMgt/ReloadDataUserTable")
	public ResponseEntity<?> ReloadDataUserTable(){
		List<Users> users = userSrvs.findAll();
		return ResponseEntity.ok(users);
	};
	
	@GetMapping("/userMgt/getUserStatisticUsingAccount")
	public ResponseEntity<?> doGetUserStatisticUsingAccount(){
		String[][] dataUsing = adminSrvs.getcountUsingAccountUser();
		return ResponseEntity.ok(dataUsing);
	};
	
	@GetMapping("/userMgt/getUserStatisticTypeAccount")
	public ResponseEntity<?> doGetUserStatisticTypeAccount(){
		String[][] dataAccountType = adminSrvs.getcountAccountTypeUser();
		return ResponseEntity.ok(dataAccountType);
	};
	
}
