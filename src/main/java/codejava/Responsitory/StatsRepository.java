package codejava.Responsitory;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import codejava.Dto.objChartOrderTotal;
import codejava.Entity.Orders;

@Repository
public interface StatsRepository extends JpaRepository<Orders, Long> {
	/*
	 * @Query(value = "{CALL sp_getTotalPriceOneMonth(:month, :year)}", nativeQuery
	 * = true) String getTotalPriceByMonth(@Param("month") String month,
	 * 
	 * @Param("year") String year); sp_getTotalPriceById('07','2021',21);
	 */
	/*
	 * @Query(value = "{CALL sp_getTotalPriceById(:month, :year, :userid) }",
	 * nativeQuery = true) String getTotalPriceById(@Param("month") String month,
	 * 
	 * @Param("year") String year,
	 * 
	 * @Param("userid")long userid);
	 */
	@Query(value = "SELECT NVL(SUM(SUM(o2.quantity)*o2.price),'0') FROM SYSTEM.orders o1 INNER JOIN SYSTEM.order_details o2 ON o1.id = o2.orderId WHERE to_char(o1.createdDate,'MM') LIKE ?1 AND to_char(o1.createdDate,'YYYY') LIKE ?2 and o1.USERID = ?3 AND o1.processid = 4 GROUP BY o2.price", nativeQuery = true)
	String getTotalPriceById(String month, String year, int userid);

	@Query(value = "SELECT COUNT(*) as total FROM SYSTEM.ORDERS ord LEFT JOIN SYSTEM.PROCESS prss ON ord.processid = prss.id WHERE ord.createddate BETWEEN ?2 AND ?3 AND prss.id = ?1", nativeQuery = true)
	String getStatisticsOrderInPeriod(int processId, Date frmDt, Date toDt);
	
	@Query(value = "SELECT NVL(SUM(sum(o2.quantity) * o2.price), '0') FROM SYSTEM.orders o1 INNER JOIN SYSTEM.order_details o2 ON o1.id = o2.orderId LEFT JOIN SYSTEM.products prod ON o2.PRODUCTID = prod.ID WHERE o2.PRODUCTID = ?1 AND to_char(o1.createdDate,'MM') LIKE ?2 AND to_char(o1.createdDate,'YYYY') LIKE ?3 GROUP BY o2.price", nativeQuery = true)
	String getStatisticsProductInPeriod(int prodId, String month, String year);

	@Query(value = "SELECT NVL(SUM(o2.quantity), '0') FROM SYSTEM.orders o1 INNER JOIN SYSTEM.order_details o2 ON o1.id = o2.orderId LEFT JOIN SYSTEM.products prod ON o2.PRODUCTID = prod.ID WHERE o2.PRODUCTID = ?1 AND to_char(o1.createdDate,'MM') LIKE ?2 AND to_char(o1.createdDate,'YYYY') LIKE ?3", nativeQuery = true)
	String getStatisticsUnitInPeriod(int prodId, String month, String year);
}
