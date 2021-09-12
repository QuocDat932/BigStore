package codejava.Services;

import codejava.Entity.TypeOfProduct;

public interface TypeOfProductServices {
	TypeOfProduct findBySlug(String flug);
}
