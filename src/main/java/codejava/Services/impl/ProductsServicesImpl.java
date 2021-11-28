package codejava.Services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.ProductsServices;

@Service
public class ProductsServicesImpl implements ProductsServices{
	@Autowired
	private ProductsRepository repo;
	
	@Override
	public List<Products> findAllAvailable() {
		// TODO Auto-generated method stub
		return repo.findAllAvailable();
	}
	
	@Override
	public Products product(int typeID) {
		Optional<Products> product = repo.findById(typeID);
		return product.isPresent() ? product.get() : null ;
	}
	
	@Override
	public List<Products> findByTypeId(Integer typeID) {
		return repo.findByTypeId(typeID);
	}
	@Override
	public List<Products> findByTypeOfID_id(Integer id) {
		// TODO Auto-generated method stub
		return repo.findByTypeOfProduct_Id(id);
	}
	@Override
	public Products findByProductsSlug(String slug) {
		// TODO Auto-generated method stub
		Optional<Products> result = repo.findBySlug(slug);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Products findById(int ID) {
		Optional<Products> result = repo.findById(ID);
		return result.isPresent() ? result.get() : null;
	}
	@Override
	public void SaveAndUpdate(Products product) {
		repo.saveAndFlush(product);
	}
	
	@Override
	@Transactional
	public void updateQuantity(Integer quantity, Integer id) {
		repo.updateQuantity(quantity, id);
	}

	@Override
	public List<Products> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Products> findAll(Pageable page) {
		return repo.findAll(page);
	}
	@Override
	public List<Products> findtop4Bytype(String type){
		List<Products> list = repo.findtop4Bytype(type);
		List<Products> listResult = new ArrayList<>();
		for(int i = 0 ;i<4;i++) {
			listResult.add(list.get(i));
		}
		return listResult;
	}

	@Override
	public List<Products> findByTypeOfProduct(TypeOfProduct type) {
		return repo.findByTypeOfProduct(type).get();
		
	}

	@Override
	public void SaveOrUpdate(Products product) {
		// TODO Auto-generated method stub
		repo.saveAndFlush(product);
		
	}

	@Override
	public List<Products> findProductAvai(int Isdeleted) {
		// TODO Auto-generated method stub
		return repo.findByIsdeleted(Isdeleted);
	};
	
	@Override
	public List<Products> findByNameLike(String nameProd) {
		return repo.findBynameContainingIgnoreCase(nameProd);
	}

	


	
}
