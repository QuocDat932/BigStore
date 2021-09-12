package codejava.Responsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import codejava.Entity.TypeOfProduct;



public interface TypesOfProductRepo extends JpaRepository<TypeOfProduct, Integer> {
	
	Optional<TypeOfProduct>  findBySlug(String slug);
}
