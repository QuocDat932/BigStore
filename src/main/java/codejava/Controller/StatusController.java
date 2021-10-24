package codejava.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/status")
public class StatusController {
	
	@RequestMapping("/{status}")
	public String getStatus(@PathVariable String status) {
		return "admin/"+status+"html";
		
	}
}
