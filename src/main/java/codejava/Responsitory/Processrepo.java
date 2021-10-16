package codejava.Responsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codejava.Constant.publicConst.Orderprocess;
import codejava.Entity.Process;

@Repository
public interface Processrepo extends JpaRepository<Process, Integer> {
	Optional<Process> findBySlug(String slug);
	
}
