package codejava.Responsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import codejava.Entity.*;

public interface TypesOfProductRepo extends JpaRepository<TypeOfProduct, Integer> {
	
	Optional<TypeOfProduct>  findBySlug(String slug);
	@Query(value = "SELECT ID, DESCRIPTION, ISDELETED, SLUG FROM SYSTEM.types_of_product WHERE isdeleted = 1", nativeQuery = true)
	List<TypeOfProduct> getListTypeOfProduct();
	
}
