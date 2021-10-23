package codejava.Controller;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import codejava.Constant.PaypalPaymentIntent;
import codejava.Constant.PaypalPaymentMethod;
import codejava.Services.PaypalService;
import codejava.Ultis.Utils;
@RestController
@RequestMapping("api")
public class PaymentController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	public static final String URL_PAYPAL_status = "pay/status";
	
	//Logger can print status in console website
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaypalService paypalService;
	
// Account Sanbox Paypal 
//	Email : sb-ukzf28264986@personal.example.com || Main Personal Account
//	Pass : yIu/8@g6 
//	Email:	sb-ukzf28264986-1@personal.example.com 
//	Pass : DATBUI123
	@GetMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") Optional<Double> price ){
		String cancelUrl = Utils.getBaseURL(request) + "/api/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/api/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price.orElse(0.0),
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				System.out.println(links.getRel());
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "ERROR";
	}
	
	@GetMapping(URL_PAYPAL_status)
	public String statusPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			return payment.getState();
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "Reject";
	}
}