package codejava.Services;

import java.util.List;

import codejava.Entity.PaymentMethod;

public interface PaymentMethodServices {
	List<PaymentMethod> listPaymentMethod() throws Exception;
}
