package codejava.Services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Constant.publicConst;
import codejava.Entity.Orders;
import codejava.Entity.Process;
import codejava.Entity.Users;
import codejava.Responsitory.OrderRepo;
import codejava.Responsitory.Processrepo;
import codejava.Services.Orderservices;
import codejava.Services.ProcessService;
@Service
public class ProcessServicesimpl implements ProcessService{
@Autowired
private Processrepo repo;
	
	
	@Override
	public Process findBySlug(publicConst.Orderprocess slug) {
		return repo.findBySlug(String.valueOf(slug)).isEmpty()?null:repo.findBySlug(String.valueOf(slug)).get();
	}

	@Override
	public List<Process> findAll() {
		return repo.findAll();
	}
	
	
}
