package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.roles;
import codejava.Responsitory.RolesRepository;
import codejava.Services.RolesServices;
@Service
public class RolesServicesImpl implements RolesServices {
	@Autowired
	private RolesRepository rolesRepo;
	@Override
	public List<roles> findAll() {
		// TODO Auto-generated method stub
		return rolesRepo.findAll();
	}
	@Override
	public roles findByID(Integer Roles_ID) {
		// TODO Auto-generated method stub
		return rolesRepo.findById(Roles_ID).isEmpty()?null:rolesRepo.findById(Roles_ID).get();
	}
	@Override
	public List<roles> findByDescription(String D) {
		// TODO Auto-generated method stub
		return rolesRepo.findByDescription(D);
	}

}
