package codejava.Controller;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import codejava.Constant.MessageAPI;
import codejava.Constant.PaypalPaymentIntent;
import codejava.Constant.PaypalPaymentMethod;
import codejava.Services.PaypalService;
import codejava.Ultis.Utils;
@Controller
@RequestMapping("home/pay/")
public class PaymentController {
	
	@RequestMapping("success")
	public String success() {
		return "home/outFrame";
	}
	@RequestMapping("cancel")
	public String cancel() {
		return "home/outFrame";
	}
}