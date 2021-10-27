package codejava.Services.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codejava.Constant.SessionConst;
import codejava.Constant.publicConst;
import codejava.Constant.publicFuncs;
import codejava.Entity.Order_Process;
import codejava.Entity.Orders;
import codejava.Entity.Process;
import codejava.Entity.Products;
import codejava.Entity.Users;
import codejava.Responsitory.OrderRepo;
import codejava.Responsitory.Userrepository;
import codejava.Services.Order_ProcessServices;
import codejava.Services.Orderservices;
import codejava.Services.ProcessService;

@Service
public class OrderServicesimpl implements Orderservices {
	@Autowired
	private OrderRepo repo;
	@Autowired
	private Userrepository Urepo;
	@Autowired
	private Order_ProcessServices ordPrcssRepo;
	@Autowired
	private ProcessService prcssRepo;

	@Override
	@Transactional
	public Orders insert(Orders order) throws Exception {
		try {
			// order.setCreateDate(publicConst.CurrentDtSQL());
			order.setCreateDate(publicConst.CurrentTimestampDtSQL());
			return repo.saveAndFlush(order);
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Orders findById(int id) {
		return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
	};

	@Override
	public List<Orders> findByUserId(int userId) {
		return repo.findByUser_idOrderByIdDesc(userId);
	};

	@Override
	public List<Orders> findByParams(int paymentMethodId, int processId, LocalDateTime frmDt, LocalDateTime toDt) {
		List<Orders> result = repo.findByProcess_idAndCreateDateBetweenOrderByIdDesc(processId, frmDt, toDt);

		return result;
	};

	@Override
	public List<Orders> findAll() {
		return repo.findAll();
	};

	@Override
	public Orders findByOrderId(int ID) {
		// TODO Auto-generated method stub
		return repo.getOrderById(ID);
	}

	@Override
	public Orders findNewOrder(Integer idUser) throws Exception {
		Users u = Urepo.findById(idUser).isPresent() ? Urepo.findById(idUser).get() : null;
		if (Objects.isNull(u)) {
			return null;
		}
		int id = repo.findNewOrder(u);
		return repo.findById(id).isEmpty() ? null : repo.findById(id).get();
	};

	@Override
	@Transactional
	public Orders ApproveOrder(int OrdId, int stepFrm, int stepTo, String NoteApprove, HttpSession session) throws Exception {
		Optional<Orders> Ordresult = null;
		Orders OrdrApproved = null;
		int rowAffect = repo.ApproveOrder(stepTo, OrdId);
		//
		if (rowAffect == 1) {
			Ordresult = repo.findById(OrdId);
			if (!Objects.isNull(Ordresult)) {
				OrdrApproved = Ordresult.isPresent() ? Ordresult.get() : null;
				// Order Process
				Order_Process orderProcess = new Order_Process();
				Users UserApproved = (Users) session.getAttribute("currentAdmin");
				orderProcess.setOrder(OrdrApproved);
				orderProcess.setProcessStep(OrdrApproved.getProcess());
				orderProcess.setUserProcess(UserApproved);
				orderProcess.setProcessdate(publicConst.CurrentTimestampDtSQL());
				orderProcess.setDescription(NoteApprove);
				
				ordPrcssRepo.Save(orderProcess);
			}
		}
		return OrdrApproved;
	}
}
