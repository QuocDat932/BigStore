package codejava.Services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface StatsServices {
	String[][] getTotalPriceByUser(int userId);
	String[][] getcountUs();
	//String[][] getTotalPriceByProd(LocalDateTime frmDate, LocalDateTime toDate, int prodId);
	String[][] getStatisticsOrderInPeriod(Date frmDate, Date toDate);
	String[][] getStatisticsProductInPeriod(int productId, Date frmDate, Date toDate, int totalMonth);
	String[][] getStatistiscUnitInPeriod(int productId, Date frmDate, Date toDate, int totalMonth);
	String[][] getStatisticQuantityOfProductByType();
	String[][] getcountUsingAccountUser();
	String[][] getcountAccountTypeUser();
	String[][] getStatisticTopUserInPeriod(Date frmDate, Date toDate);
}
