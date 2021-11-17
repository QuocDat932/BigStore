package codejava.Responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import codejava.Entity.Orders;
@Repository
public interface StatsRepository extends JpaRepository<Orders, Long>{
	/*@Query(value = "{CALL sp_getTotalPriceOneMonth(:month, :year)}", nativeQuery = true)
	String getTotalPriceByMonth(@Param("month") String month, 
			@Param("year") String year);
	sp_getTotalPriceById('07','2021',21);*/
	/*@Query(value = "{CALL sp_getTotalPriceById(:month, :year, :userid) }", nativeQuery = true)
	String getTotalPriceById(@Param("month") String month, 
								@Param("year")  String year,
								@Param("userid")long userid);*/
	@Query(value ="SELECT NVL(SUM(o2.price),'0') FROM SYSTEM.orders o1 INNER JOIN SYSTEM.order_details o2 ON o1.id = o2.orderId WHERE to_char(o1.createdDate,'MM') LIKE ?1 AND to_char(o1.createdDate,'YYYY') LIKE ?2 and o1.USERID = ?3 AND o1.processid = 4", nativeQuery = true)
	String getTotalPriceById(String month, String year, int userid );
}
