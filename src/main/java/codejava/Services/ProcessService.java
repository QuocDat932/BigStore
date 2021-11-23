package codejava.Services;


import java.util.List;
import java.util.Optional;

import codejava.Constant.publicConst;
import codejava.Entity.Process;

public interface ProcessService {
	Process findBySlug(publicConst.Orderprocess slug);
	List<Process> findAll();
	Process findById(int id);
}
