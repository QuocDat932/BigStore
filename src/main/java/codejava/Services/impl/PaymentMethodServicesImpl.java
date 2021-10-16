package codejava.Services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Entity.PaymentMethod;
import codejava.Responsitory.PaymentMethodrepo;
import codejava.Services.PaymentMethodServices;

@Service
public class PaymentMethodServicesImpl implements PaymentMethodServices{
	@Autowired
	private PaymentMethodrepo repo;
	
	@Override
	public List<PaymentMethod> listPaymentMethod() throws Exception {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
