package codejava.Services.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.ProcessorResponse;

import codejava.Dto.objChartOrderTotal;
import codejava.Entity.Process;
import codejava.Responsitory.RolesRepository;
import codejava.Responsitory.StatsRepository;
import codejava.Services.ProcessService;
import codejava.Services.StatsServices;
import codejava.Constant.publicFuncs;

@Service
public class StatsServicesImpl implements StatsServices {
	@Autowired
	private StatsRepository repo;
	@Autowired
	private RolesRepository repoRoles;
	@Autowired
	private ProcessService processServs;

	@Override
	public String[][] getTotalPriceByUser(int userId) {
		String[][] result = new String[2][6];
		YearMonth thisMonth = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = thisMonth.minusMonths((long) i).getMonthValue() + "";
			String year = thisMonth.minusMonths((long) i).getYear() + "";
			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			result[0][5 - i] = month + "-" + year;
			result[1][5 - i] = repo.getTotalPriceById(month, year, userId);
		}
		return result;
	}

	@Autowired
	public String[][] getcountUs() {
		List<String> roles = new ArrayList<String>();
		roles.add("admin");
		roles.add("user");

		String[][] result = new String[roles.size()][roles.size()];
		int size = roles.size();
		for (int i = 0; i < size; i++) {
			result[0][size - (i + 1)] = roles.get(i);
			result[1][size - (i + 1)] = repoRoles.CountUS(roles.get(i));
		}
		return result;
	};

	@Override
	public String[][] getStatisticsOrderInPeriod(Date frmDate, Date toDate) {
		List<Process> listProcess = processServs.findAll();
		int size = listProcess.size();
		String[][] result = new String[2][size];
		for (int i = 0; i < size; i++) {
			result[0][size - (i + 1)] = listProcess.get(i).getDescription();
			result[1][size - (i + 1)] = repo.getStatisticsOrderInPeriod(listProcess.get(i).getId(), frmDate, toDate);
		};
		return result;
	};

	@Override
	public String[][] getStatisticsProductInPeriod(int productId, Date frmDate, Date toDate, int totalMonth){
		String[][] result = new String[2][totalMonth];
		YearMonth thisMonth = YearMonth.now();
		for (int i = 0; i < totalMonth; i++) {
			String month = thisMonth.minusMonths((long) i).getMonthValue() + "";
			String year	 = thisMonth.minusMonths((long) i).getYear()+"";
			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			result[0][totalMonth - (i + 1)] = month +"-"+ year;
			result[1][totalMonth - (i + 1)] = repo.getStatisticsProductInPeriod(productId, month, year);
		};
		return result;
	};
	
	@Override
	public String[][] getStatistiscUnitInPeriod(int productId, Date frmDate, Date toDate, int totalMonth){
		String[][] result = new String[2][totalMonth];
		YearMonth thisMonth = YearMonth.now();
		for (int i = 0; i < totalMonth; i++) {
			String month = thisMonth.minusMonths((long) i).getMonthValue() + "";
			String year	 = thisMonth.minusMonths((long) i).getYear()+"";
			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			result[0][totalMonth - (i + 1)] = month +"-"+ year;
			result[1][totalMonth - (i + 1)] = repo.getStatisticsUnitInPeriod(productId, month, year);
		};
		return result;
	}
}
