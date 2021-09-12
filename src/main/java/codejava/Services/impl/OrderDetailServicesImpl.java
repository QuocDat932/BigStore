package codejava.Services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BuiQuocDat.dto.cartDetailDto;
import com.example.BuiQuocDat.respository.OrderDetailrepo;
import com.example.BuiQuocDat.services.OrderDetailServices;
@Service
public class OrderDetailServicesImpl implements OrderDetailServices{
	@Autowired
	private OrderDetailrepo repo;
	@Override
	@Transactional
	public void insert(cartDetailDto cartDetailDto) throws Exception {
		repo.insert(cartDetailDto);
		
	}
}
