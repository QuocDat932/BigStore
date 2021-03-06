package codejava.Services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.Products;
import codejava.Entity.TypeOfProduct;
import codejava.Entity.Users;
import codejava.Services.*;
import codejava.Responsitory.*;

@Service
public class TypeOfProductImpl implements TypeOfProductServices{
	
	@Autowired
	private TypesOfProductRepo repo;
	
	@Override
	public TypeOfProduct findBySlug(String flug) {
		Optional<TypeOfProduct> result = repo.findBySlug(flug);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public List<TypeOfProduct> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public List<TypeOfProduct> getListTypeOfProduct(){
		return repo.getListTypeOfProduct();
	};

	@Override
	public TypeOfProduct findById(Integer id){
		Optional<TypeOfProduct> result = repo.findById(id);
		return result.isPresent() ? result.get() : null;
	}
}
