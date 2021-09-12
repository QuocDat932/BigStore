package codejava.Services;
import java.util.*;
import codejava.Entity.*;
public interface RolesServices  {
	List<roles> findAll();
	roles add(roles r);
	roles findByUserID(Integer roles);
}
