package codejava.Services;

import java.util.List;

import codejava.Entity.Order_Process;

public interface Order_ProcessServices {
	List<Order_Process> lstOrder_Process() throws Exception;
	List<Order_Process> lstOrder_ProcessByOrdId(int ordId) throws Exception;
}
