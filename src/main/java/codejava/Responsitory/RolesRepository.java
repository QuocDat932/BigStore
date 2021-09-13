package codejava.Responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import codejava.Entity.roles;
=======
import org.springframework.stereotype.Repository;
import codejava.Entity.*;
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb

@Repository
public interface RolesRepository extends JpaRepository<roles, Long> {
	roles findById(Integer id);
<<<<<<< HEAD
	

=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
}
