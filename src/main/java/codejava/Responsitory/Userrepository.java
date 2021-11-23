package codejava.Responsitory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codejava.Entity.Users;
import codejava.Entity.roles;

import codejava.Entity.Users;
@Repository
public interface Userrepository extends JpaRepository<Users, Integer>{
	Users findByfullname(String fullname);
	
/* Users findByUsernameAndHashPassword(String username, String hashpassword); */
	
 	Optional<Users> findByEmail(String email);
	
	List<Users> findByRoleIn(List<roles> roles);
	
	
	/*@Query(value = "SELECT hashPassword FROM users WHERE username = ?1", nativeQuery = true)
	Optional<Users> findby(String hashPassword);*/
	
	
}
