package codejava.Responsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import codejava.Entity.*;

@Repository
public interface RolesRepository extends JpaRepository<roles, Integer> {
	Optional<roles> findById(Integer id);
	List<roles> findByDescription(String D);
}
