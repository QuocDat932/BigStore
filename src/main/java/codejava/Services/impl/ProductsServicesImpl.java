package codejava.Services.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Products;
import codejava.Responsitory.ProductsRepository;
import codejava.Services.ProductsServices;

@Service
public class ProductsServicesImpl implements ProductsServices{
	@Autowired
	private ProductsRepository repo;

	@Override
	public List<Products> findAllAvailable() {
		return repo.findAllAvailable();
	}

	@Override
	public Products findById(Long ID) {
		// TODO Auto-generated method stub
		return repo.findById(ID).isEmpty()?null:repo.findById(ID).get();
	}

	@Override
	public Products product(Long typeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> findByTypeId(Integer typeID) {
		return repo.findByTypeId(typeID);
	}

	@Override
	public List<Products> findByTypeOfID_id(Integer id) {
		return null;
	}

	@Override
	public Products findByProductsSlug(String slug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveAndUpdate(Products product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuantity(Integer quantity, Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
