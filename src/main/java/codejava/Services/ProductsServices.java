package codejava.Services;

import java.util.List;

import codejava.Entity.Products;

public interface ProductsServices {
	List<Products> findAllAvailable();
	
	Products findById(int ID);
	//List<Products> findByTypeId(Integer typeID);
	Products product(int typeID);
	//
	List<Products> findByTypeId(Integer typeID);
	
	List<Products> findByTypeOfID_id(Integer id);
	
	Products findByProductsSlug(String slug);
	
	void SaveAndUpdate(Products product);
	
	void updateQuantity(Integer quantity, Integer id);
}
