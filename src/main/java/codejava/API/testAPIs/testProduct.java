package codejava.API.testAPIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.ProductsServices;
import codejava.Services.TypeOfProductServices;

@RestController
@RequestMapping("/api")
public class testProduct {
	
@Autowired
private ProductsRepository repoP;

@Autowired
private TypeOfProductServices ServC;

@Autowired
private ProductsServices ServP;

	@GetMapping("/top/cate")
	public ResponseEntity<List<Products>> getTop4TypeofProduct(){
		
	List<TypeOfProduct> p = ServC.findAll();
	p.forEach(c ->{
		System.out.println("Type : " +c.getDescription());
	});
	
	
		List<Products> p1  = repoP.findTop4BySlug("qua-thom").get();
		p1.forEach(c ->{
			System.out.println("find slug : " +c.getName());
		});
		
		return ResponseEntity.ok(p1);
	}
	@GetMapping("/page")
	public ResponseEntity<?> getExchangePage(@RequestParam Optional<Integer> p){
		List<Products> l = ServP.findAll();
		int numP = 8;
		int max = l.size()-numP;
		int page = p.orElse(0) <= 0 ? 0 : p.get(); 
		int page1 = page*numP;
		while(page1 > max) {
			 page1 -=numP;
			 page-=1;
		}
		List<Products> l1 = new ArrayList<Products>();
		for (int i = page1; i <= page1+numP-1; i++) {
			l1.add(l.get(i));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("current", page);
		map.put("back", page-1<0?page:page-1);
		map.put("next", page+numP >=max?page:page+1);
		map.put("products", l1);
		return ResponseEntity.ok(map);
	};
	@GetMapping("/type")
	public ResponseEntity<?> getTypeofProduct(@RequestParam Optional<String> slug,@RequestParam Optional<Integer> p){
		ServP.findByTypeOfProduct(ServC.findBySlug(slug.orElse("RAU")));
		
		
		List<Products> l = ServP.findByTypeOfProduct(ServC.findBySlug(slug.orElse("RAU")));
		int numP = 4;
		int max = l.size()-numP;
		int page = p.orElse(0) <= 0 ? 0 : p.get(); 
		int page1 = page*numP;
		while(page1 > max) {
			 page1 -=numP;
			 page-=1;
		}
		List<Products> l1 = new ArrayList<Products>();
		for (int i = page1; i <= page1+numP-1; i++) {
			l1.add(l.get(i));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("current", page);
		map.put("back", page-1<0?page:page-1);
		map.put("next", page+numP >=max?page:page+1);
		map.put("products", l1);
		return ResponseEntity.ok(map);
	}
	
}
