package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Payee;
@Service
public interface PayeeService {
	List<Payee> getPayeeList() throws Exception;
	Payee getPayeeById(Integer id) throws Exception;
	void addPayee(Payee payee) throws Exception;
	void editPayee(Payee payee) throws Exception;
	void deletePayee(Integer id) throws Exception;

	// ページ分割機能用
	int getTotalPages(int numPerPage) throws Exception;
	List<Payee> getPayeeListByPage(int page, int numPerPage) throws Exception;

}
