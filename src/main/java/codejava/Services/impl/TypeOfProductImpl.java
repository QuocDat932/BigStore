package codejava.Services.impl;
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
}
