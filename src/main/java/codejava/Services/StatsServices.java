package codejava.Services;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsServices {
	String[][] getTotalPriceByUser(int userId);
	String[][] getcountUs();
	//String[][] getTotalPriceByProd(LocalDateTime frmDate, LocalDateTime toDate, int prodId);
}
