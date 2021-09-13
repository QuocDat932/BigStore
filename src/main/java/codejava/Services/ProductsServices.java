package codejava.Services;

import java.util.List;

import codejava.Entity.Products;

public interface ProductsServices {
	List<Products> findAllAvailable();
	
<<<<<<< HEAD
	Products findById(Long ID);
	//List<Products> findByTypeId(Integer typeID);
	Products product(Long typeID);
=======
	Products findById(int ID);
	//List<Products> findByTypeId(Integer typeID);
	Products product(int typeID);
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
	//
	List<Products> findByTypeId(Integer typeID);
	
	List<Products> findByTypeOfID_id(Integer id);
	
	Products findByProductsSlug(String slug);
	
	void SaveAndUpdate(Products product);
	
<<<<<<< HEAD
	void updateQuantity(Integer quantity, Long id);
=======
	void updateQuantity(Integer quantity, Integer id);
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
}
