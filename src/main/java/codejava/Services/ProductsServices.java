package codejava.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;

public interface ProductsServices {
	List<Products> findAllAvailable();
	
	List<Products> findAll();
	
	Products findById(int ID);
	
	Products product(int typeID);
	//
	List<Products> findByTypeId(Integer typeID);
	
	List<Products> findByTypeOfID_id(Integer id);
	
	Products findByProductsSlug(String slug);
	
	void SaveOrUpdate(Products product);

	void SaveAndUpdate(Products product);

	void updateQuantity(Integer quantity, Integer id);

	Page<Products> findAll(Pageable page);
	List<Products> findtop4Bytype(String type);
	
	List<Products> findByTypeOfProduct(TypeOfProduct type);
}
