package codejava.API.APIADM;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Constant.publicFuncs;
import codejava.Entity.OrderDetails;
import codejava.Entity.Order_Process;
import codejava.Entity.Orders;
import codejava.Responsitory.OrderRepo;
import codejava.Services.OrderDetailServices;
import codejava.Services.Order_ProcessServices;
import codejava.Services.Orderservices;
import codejava.Services.StatsServices;

@RestController
@RequestMapping("/api/admin/")
public class apiAdmOrder {
	@Autowired
	private Orderservices OrdServs;
	
	@Autowired
	private OrderDetailServices ordDtlServs;
	
	@Autowired
	private Order_ProcessServices ordPrcssServs;
	
	@Autowired
	private StatsServices statsServs;
	
	@GetMapping("order/getLstOrder")
	public ResponseEntity<?> doGetLstOrder(){
		List<Orders> result = OrdServs.findAll();
		return ResponseEntity.ok(result);
	};
	@GetMapping("order/getLstOrderDtl")
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
	@GetMapping("order/getOrder_Process")
	public ResponseEntity<?> doGetOrder_Process(@RequestParam("ordId") int ordId) throws Exception{
		return ResponseEntity.ok(ordPrcssServs.lstOrder_ProcessByOrdId(ordId));
	}
	@GetMapping("order/getLstOrderByParam")
	public ResponseEntity<?> doGetLstOrderByParam(@RequestParam("processId") int prcssId,
												  @RequestParam("frmOrderDate") Date frmOrderDate,
												  @RequestParam("toOrderDate") Date toOrderDate){
	    int datex = toOrderDate.getDate()+1;
	    toOrderDate.setDate(datex);
		LocalDateTime fm = publicFuncs.convertToLocalDateTimeViaMilisecond(frmOrderDate);
		LocalDateTime to = publicFuncs.convertToLocalDateTimeViaMilisecond(toOrderDate);
		return ResponseEntity.ok(OrdServs.findByParams(1, prcssId, fm, to));
	};
	@GetMapping("order/approveNextSetp")
	public ResponseEntity<?> doGetApproveNextSetp(@RequestParam("ordId") int ordId,
												  @RequestParam("stepFrm") int stepFrm,
												  @RequestParam("stepTo") int stepTo,
												  @RequestParam("desc") String NoteApprove,
												  HttpSession session) throws Exception{
		Orders result = OrdServs.ApproveOrder(ordId, stepFrm, stepTo, NoteApprove,session);
		return ResponseEntity.ok(result);
	};
	@GetMapping("order/stepProcess")
	public ResponseEntity<?> doGetStepProcess(@RequestParam("ordId") int ordId) throws Exception{
		List<Order_Process> result = ordPrcssServs.lstOrder_ProcessByOrdId(ordId);
		return ResponseEntity.ok(result);
	};
	@GetMapping("order/getStatisticsOrderInPeriod")
	public ResponseEntity<?> doGetStatisticsOrderInPeriod(@RequestParam("frmDt") Date frmDt, @RequestParam("toDt") Date toDt){
		String[][] result = statsServs.getStatisticsOrderInPeriod(frmDt, toDt);
		return ResponseEntity.ok(result);
	}
}
