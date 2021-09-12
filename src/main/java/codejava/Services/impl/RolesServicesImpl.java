package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BuiQuocDat.entity.roles;
import com.example.BuiQuocDat.respository.RolesRepository;
import com.example.BuiQuocDat.services.RolesServices;
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
}
