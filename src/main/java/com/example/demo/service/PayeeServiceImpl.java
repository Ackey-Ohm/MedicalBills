package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PayeeDao;
import com.example.demo.domain.Payee;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	PayeeDao payeeDao;

	@Override
	public List<Payee> getPayeeList() throws Exception {
		return payeeDao.selectAll();
	}

	@Override
	public Payee getPayeeById(Integer id) throws Exception {
		return payeeDao.selectById(id);
	}

	@Override
	public void addPayee(Payee payee) throws Exception {
		payeeDao.insert(payee);
	}

	@Override
	public void editPayee(Payee payee) throws Exception {
		payeeDao.update(payee);
	}

	@Override
	public void deletePayee(Integer id) throws Exception {
		payeeDao.delete(id);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		long totalNum = payeeDao.count();
		totalNum = (totalNum * 2) / numPerPage + 1;
		return (int) totalNum / 2;
	}

	@Override
	public List<Payee> getPayeeListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return payeeDao.selectLimited(offset, numPerPage);
	}

}
