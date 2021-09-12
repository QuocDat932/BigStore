package codejava.Services.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BuiQuocDat.entity.Products;
import com.example.BuiQuocDat.entity.TypeOfProduct;
import com.example.BuiQuocDat.entity.Users;
import com.example.BuiQuocDat.services.*;
import com.example.BuiQuocDat.respository.*;

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
