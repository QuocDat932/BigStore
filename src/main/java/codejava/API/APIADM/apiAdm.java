package codejava.API.APIADM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Users;
import codejava.Services.UserServices;

@RestController
@RequestMapping("/api/admin")
public class apiAdm {
	@Autowired
	private UserServices userSrvs;
	@GetMapping("/indexUser")
	public ResponseEntity<?> doGetIndexUser(@RequestParam("userId") int userId){
		Users userIndex = userSrvs.findByid(userId);
		System.out.println("/api/admin/indexUser > img = " + userIndex.getImgUrl());
		return ResponseEntity.ok(userIndex);
	}
}
