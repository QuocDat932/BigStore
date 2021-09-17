package codejava.Services;
import java.util.*;
import codejava.Entity.*;
public interface RolesServices  {
	List<roles> findAll();
	List<roles> findByDescription(String D);
	roles findByID(Integer roles);
}
