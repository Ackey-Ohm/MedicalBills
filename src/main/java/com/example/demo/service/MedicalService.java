package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Medical;

public interface MedicalService {
	List<Medical> getMedicalList() throws Exception;
	Medical getMedicalById(Integer id) throws Exception;
	void addMedical(Medical medical) throws Exception;
	void editMedical(Medical medical) throws Exception;
	void deleteMedical(Integer id) throws Exception;

	// ページ分割機能用
	int getTotalPages(int numPerPage) throws Exception;
	List<Medical> getMedicalListByPage(int page, int numPerPage) throws Exception;

}
