package codejava.API.APIADM;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codejava.Services.StatsServices;
import codejava.Services.TypeOfProductServices;

@RestController
@RequestMapping("/api/admin/")
public class apiAdmProductMgt {
	@Autowired
	private StatsServices statsServs;
	
	@GetMapping("product/getProductOrderStatistics")
	public ResponseEntity<?> doGetProductOrderStatistics(@RequestParam("prodId") int prodId,
														 @RequestParam("frmDate") Date frmDate,
														 @RequestParam("toDate") Date toDate,
														 @RequestParam("totalMonth") int totalMonth){
		String[][] result = statsServs.getStatisticsProductInPeriod(prodId, frmDate, toDate, totalMonth);
		return ResponseEntity.ok(result);
	};
	@GetMapping("product/getStatistiscUnitInPeriod")
	public ResponseEntity<?> doGetStatistiscUnitInPeriod(@RequestParam("prodId") int prodId,
														 @RequestParam("frmDate") Date frmDate,
														 @RequestParam("toDate") Date toDate,
														 @RequestParam("totalMonth") int totalMonth){
		String[][] result = statsServs.getStatistiscUnitInPeriod(prodId, frmDate, toDate, totalMonth);
		return ResponseEntity.ok(result);
	};
	@GetMapping("product/getStatisticQuantityOfProductByType")
	public ResponseEntity<?> doGetStatisticQuantityOfProductByType(){
		String[][] result = statsServs.getStatisticQuantityOfProductByType();
		return ResponseEntity.ok(result);
	}
	
}
