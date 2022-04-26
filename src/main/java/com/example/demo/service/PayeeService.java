package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Payee;
@Service
public interface PayeeService {
	List<Payee> getMemberList() throws Exception;
	Payee getMemberById(Integer id) throws Exception;
	void addMember(Payee payee) throws Exception;
	void editMember(Payee payee) throws Exception;
	void deleteMember(Integer id) throws Exception;

	// ページ分割機能用
	int getTotalPages(int numPerPage) throws Exception;
	List<Payee> getMemberListByPage(int page, int numPerPage) throws Exception;

}
