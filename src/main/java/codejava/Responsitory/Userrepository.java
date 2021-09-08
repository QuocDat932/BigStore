package codejava.Responsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codejava.Entity.userObj;
@Repository
public interface Userrepository extends JpaRepository<userObj, Integer>{
	List<userObj> findAll();
	
	@Query(value = "SELECT * FROM SYSTEM.SYS_USERS WHERE 1=?1", nativeQuery = true)
	List<userObj> all(int required1);
	
	Optional<userObj> findById(int id);
}
