package codejava.API.APIUSR;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Entity.OrderDetails;
import codejava.Entity.Orders;
import codejava.Entity.Users;
import codejava.Services.OrderDetailServices;
import codejava.Services.Orderservices;
import codejava.Services.PaymentMethodServices;
import codejava.Services.ProcessService;

@RestController
@RequestMapping("/api/order")
public class APIOrders {
	@Autowired 
	private Orderservices orderServ;
	@Autowired
	private OrderDetailServices ordDtlServs;
	@Autowired
	private PaymentMethodServices paymntMthdServ;
	@Autowired
	private ProcessService processServr;
	@GetMapping("/GetPaymentMethod")
	public ResponseEntity<?> doGetPaymentMethod() throws Exception{
		return ResponseEntity.ok(paymntMthdServ.listPaymentMethod());
	};
	@GetMapping("/GetProcess")
	public ResponseEntity<?> doGetProcess(HttpSession session) throws Exception{
		Users currentAccount = (Users) session.getAttribute("currentAdmin");
		return ResponseEntity.ok(processServr.findAll());
	};
	/*@GetMapping("/orderDataByParam")
	public ResponseEntity<?> doGetOrderDataByParam(@RequestParam("paymentMethodId") int paymentMethodId,
												   @RequestParam("processId") int processId,
												   @RequestParam("frmOrderDt") Date frmOrderDt,
												   @RequestParam("toOrderDt") Date toOrderDt,
												   HttpSession session){
		if(session.getAttribute("currentUser") == null) {
		return ResponseEntity.ok(null);
		}else {
		Users userCurrent = (Users) session.getAttribute("currentUser");
		List<Orders> result = orderServ.findByParams(userCurrent.getId(), paymentMethodId, processId, frmOrderDt, toOrderDt);		
		return ResponseEntity.ok(result);
		}
	};*/
	@GetMapping("/AllorderData")
	public ResponseEntity<?> doGetOrderDataByParam(HttpSession session){
		if(session.getAttribute("currentUser") == null) {
		return ResponseEntity.ok(null);
		}else {
		Users userCurrent = (Users) session.getAttribute("currentUser");
		List<Orders> result = orderServ.findByUserId(userCurrent.getId());		
		return ResponseEntity.ok(result);
		}
	}
	
	@PostMapping("/RejectOrder")
	public ResponseEntity<?> doPostRejectOrder(@RequestParam("ordId") int ordId,
											   @RequestParam("reasonReject") Optional<String> reasonReject,
											   HttpSession session)throws Exception {
		Orders orderReject = orderServ.findById(ordId);
		String reasonRejectString = reasonReject.orElse("No Reasion") + "";
		Orders result = orderServ.ApproveOrder(ordId, orderReject.getProcess().getId(), 5,reasonRejectString, session);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getLstOrderDtl")
	public ResponseEntity<?> doGetLstOrderDtl(@RequestParam("ordId") int ordId) throws Exception{
		List<OrderDetails> result;
		try {
			result = ordDtlServs.listOrdDtlById(ordId);
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return ResponseEntity.ok(result);
	};
}
