package codejava.Services;
import java.util.List;

import codejava.Entity.*;
public interface TypeOfProductServices {
	TypeOfProduct findBySlug(String flug);
	List<TypeOfProduct> findAll();
}
