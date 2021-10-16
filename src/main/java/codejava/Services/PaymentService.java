package codejava.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codejava.Dto.*;
import codejava.Entity.PaymentMethod;
import codejava.Entity.Users;

public interface PaymentService {
	PaymentMethod findById(int id);
}
