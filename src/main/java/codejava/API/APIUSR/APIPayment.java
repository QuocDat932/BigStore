package codejava.API.APIUSR;
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
@RestController
@RequestMapping("api")
public class APIPayment {
	public static final String URL_PAYPAL_SETSTS = "/pay/setstatus";
	public static final String URL_PAYPAL_GETSTS = "/pay/getstatus";
	public static final String URL_PAYPAL_SUCCESS = "home/pay/success";
	public static final String URL_PAYPAL_CANCEL = "home/pay/cancel";
	;
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
	public ResponseEntity<?> pay(HttpServletRequest request,@RequestParam("price") double price ){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return ResponseEntity.ok(MessageAPI.message("Submited", "everything is done", links.getHref()));
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok(MessageAPI.message("Failed", "Something Wrong", null));
	}
	@GetMapping(URL_PAYPAL_SETSTS)
	public ResponseEntity<?> setStatusPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			System.out.println("app");
			MessageAPI.STATUSPAYPAL = "approved";
			return ResponseEntity.ok(payment.getState());
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
		}
		System.out.println("Rej");
		MessageAPI.STATUSPAYPAL = "Reject";
		return ResponseEntity.ok("Reject");
	}
	@GetMapping(URL_PAYPAL_GETSTS)
	public ResponseEntity<?> getStatusPay(){
		boolean a = MessageAPI.STATUSPAYPAL==null||MessageAPI.STATUSPAYPAL.length()==0;
		return ResponseEntity.ok(a?MessageAPI.message("Faild", "Reject Paypal", MessageAPI.STATUSPAYPAL):MessageAPI.message("Submit", "Paypal Approred",  MessageAPI.STATUSPAYPAL));
	}
}