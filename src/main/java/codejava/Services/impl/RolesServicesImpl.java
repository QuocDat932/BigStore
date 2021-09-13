package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.roles;
import codejava.Responsitory.RolesRepository;
<<<<<<< HEAD
import codejava.Responsitory.UsersRepository;
=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
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
	public roles findByUserID(Integer Roles_ID) {
		// TODO Auto-generated method stub
		return rolesRepo.findById(Roles_ID);
	}
<<<<<<< HEAD
	@Override
	public roles add(roles r) {
		
		return null;
	}
=======
>>>>>>> d621f6800c23e23386ea6d0c49b36e0400ec41fb
}
