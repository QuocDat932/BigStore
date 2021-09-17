package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import codejava.Entity.*;

@Repository
public interface RolesRepository extends JpaRepository<roles, Long> {
	roles findById(Integer id);
	@Query(value = "SELECT  COUNT(rl.id) FROM SYSTEM.users us LEFT JOIN SYSTEM.roles rl on us.roleid = rl.id WHERE rl.description LIKE ?1", nativeQuery = true)
	String CountUS(String roles);
}
