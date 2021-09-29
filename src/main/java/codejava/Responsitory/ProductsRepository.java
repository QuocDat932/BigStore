package codejava.Responsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import codejava.Entity.*;

@Repository								
public interface ProductsRepository extends JpaRepository<Products, Integer>{
	//List<Products> findByTypeId(Integer TypeId);
	@Query(value = " SELECT * FROM products Where isdeleted = 0", nativeQuery = true)
	List<Products> findAllAvailable();
	
	@Query(value="SELECT * FROM products WHERE typeId = ?1", nativeQuery = true)
	List<Products> findByTypeId(Integer typeID);
	
	List<Products> findByTypeOfProduct_Id(Integer id);
	
	Optional<List<Products>> findTop4BySlug(String slug);
	
	Optional<Products> findBySlug(String slug);
	
	@Modifying(clearAutomatically = true)
	@Query(value=" UPDATE products SET quantity = ?1 Where id = ?2", nativeQuery = true)
	void updateQuantity(Integer quantity, Integer ID);
	
	Page<Products> findAll(Pageable page);
	@Query(value="SELECT pro.* FROM SYSTEM.products pro LEFT JOIN SYSTEM.types_of_product types ON pro.TYPEID = types.ID\r\n"
			+ "WHERE pro.isdeleted = 1 and types.isdeleted = 1 and types.slug = ?1 and rownum < 5 order by pro.id desc", nativeQuery = true)
	List<Products> findtop4Bytype(String type);
	
/*
 * ----------------------------------->1
 @Query(value = " SELECT * FROM product Where isDeleted = 0 ?1 and price = ?2", nativeQuery = true)
	List<Products> findAllAvailable();
 * ----------------------------------------->2
 @Modifying(clearAutomatically = true)
 @Query(value = "INSERT INTO booking(userId, tableId, bookedTime, bookedStatus, startTime) VALUES "
			+ "(:#{#dto.userId}, :#{#dto.tableId}, :#{#dto.bookedTime}, "
			+ ":#{#dto.bookedStatus}, :#{#dto.startTime})",
			nativeQuery = true)
	void insertByDto(BookingDto dto);
 */

}
