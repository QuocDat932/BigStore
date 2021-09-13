package codejava.Services;
import java.util.*;
import codejava.Entity.*;
public interface RolesServices  {
	List<roles> findAll();
	
	roles findByUserID(Integer roles);
}
