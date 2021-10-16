package codejava.Services.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import codejava.Dto.*;
import codejava.Entity.*;
import codejava.Responsitory.PaymentRepo;
import codejava.Services.*;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo repo;
	
	@Override
	public PaymentMethod findById(int id) {
		return repo.findById(id).isEmpty()?null:repo.findById(id).get();
	}
	

}
