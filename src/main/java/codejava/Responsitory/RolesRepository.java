package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Entity.roles;


@Repository
public interface RolesRepository extends JpaRepository<roles, Long> {
	roles findById(Integer id);
}
