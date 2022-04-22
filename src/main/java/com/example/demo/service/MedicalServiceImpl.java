package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.MedicalDao;
import com.example.demo.domain.Medical;

public class MedicalServiceImpl implements MedicalService {

	@Autowired
	MedicalDao medicalDao;

	@Override
	public List<Medical> getMedicalList() throws Exception {
		return medicalDao.selectAll();
	}

	@Override
	public Medical getMedicalById(Integer id) throws Exception {
		return medicalDao.selectById(id);
	}

	@Override
	public void addMedical(Medical medical) throws Exception {
		medicalDao.insert(medical);
	}

	@Override
	public void editMedical(Medical medical) throws Exception {
		medicalDao.update(medical);
	}

	@Override
	public void deleteMedical(Integer id) throws Exception {
		medicalDao.delete(id);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		long totalNum = medicalDao.count();
		totalNum = (totalNum * 2) / numPerPage + 1;
		return (int) totalNum / 2;
	}

	@Override
	public List<Medical> getMedicalListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return medicalDao.selectLimited(offset, numPerPage);
	}


}
