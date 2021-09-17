package codejava.Services.impl;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Responsitory.RolesRepository;
import codejava.Responsitory.StatsRepository;
import codejava.Services.StatsServices;
@Service
public class StatsServicesImpl implements StatsServices{
	@Autowired
	private StatsRepository repo;
	@Autowired
	private RolesRepository repoRoles;
	@Override
	public String[][] getTotalPrice6Months() {
		String[][] result = new String[2][6];
		YearMonth thisMonth = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = thisMonth.minusMonths((long)i).getMonthValue() + "";
			String year = thisMonth.minusMonths((long)i).getYear() + "";
			result[0][5-i] = month + "-" + year;
			result[1][5-i] = repo.getTotalPriceByMonth(month, year);
		}
		return result;
	};
	public String[][] getcountUs(){
		
		List<String> roles = new ArrayList<String>();
		roles.add("admin");
		roles.add("user");
		
		String[][] result = new String[roles.size()][roles.size()];
		int size = roles.size();
		for(int i = 0 ; i < size ; i++) {
			result[0][size - (i+1)] = roles.get(i);
			result[1][size - (i+1)] = repoRoles.CountUS(roles.get(i));
			//System.out.println("line 1 - "+i+ " "+ result[0][size - (i+1)]);
			//System.out.println("line 2 - "+i+ " "+ result[1][size - (i+1)]);
		}
		return result;
	}
}
