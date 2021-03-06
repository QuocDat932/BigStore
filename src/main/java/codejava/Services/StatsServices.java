package codejava.Services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import codejava.Dto.objChart;

public interface StatsServices {
	String[][] getTotalPriceByUser(int userId, int totalMonth);
	String[][] getcountUs();
	//String[][] getTotalPriceByProd(LocalDateTime frmDate, LocalDateTime toDate, int prodId);
	String[][] getStatisticsOrderInPeriod(Date frmDate, Date toDate);
	String[][] getStatisticsProductInPeriod(int productId, Date frmDate, Date toDate, int totalMonth);
	String[][] getStatistiscUnitInPeriod(int productId, Date frmDate, Date toDate, int totalMonth);
	String[][] getStatisticQuantityOfProductByType();
	String[][] getcountUsingAccountUser();
	String[][] getcountAccountTypeUser();
	List<objChart> getStatisticTopUserTotalPriceByUserIdInPeriod();
}
