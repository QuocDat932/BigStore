package codejava.API.testAPIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codejava.Responsitory.ProductsRepository;

@RestController
@RequestMapping("/api")
public class testProduct {
@Autowired
private ProductsRepository repoP;

	
	public ResponseEntity<?> getTop4TypeofProduct(){
//		repoP.findTop4BySlug("luong-kho").isPresent()?repoP.findTop4BySlug("luong-kho").get();
		
		
		return ResponseEntity.ok("");
	}
	
}
