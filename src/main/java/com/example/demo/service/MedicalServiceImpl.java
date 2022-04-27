package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MedicalDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dao.PayeeDao;
import com.example.demo.domain.Medical;
@Service
public class MedicalServiceImpl implements MedicalService {

	@Autowired
	MedicalDao medicalDao;
	@Autowired
	MemberDao memberDao;
	@Autowired
	PayeeDao payeeDao;

	@Override
	public List<Medical> getMedicalList() throws Exception {
		System.out.println("MedicalServiceImpl::list() start.");
		List<Medical> medicalList = medicalDao.selectAll();
		for (Medical medical : medicalList) {
			System.out.println("MedicalServiceImpl::medicalList dump\n"
					+ "\tid: " + medical.getId()
					+ "\tmember: " + medical.getMember()
					+ "\tpayee: " + medical.getPayee()
					+ "\tamount: " + medical.getAmount()
					+ "\tdateUse: " + medical.getDateUse()
					+ "\titemType: " + medical.getItemType()
					+ "\tclass1: " + medical.getClass1()
					+ "\tclass2: " + medical.getClass2()
					+ "\tclass3: " + medical.getClass3()
					+ "\tclass4: " + medical.getClass4()
					+ "\tdescription: " + medical.getDescription()
					);

			medical.setMemberName(memberDao.selectById(medical.getMember()).getName());
			medical.setPayeeName(payeeDao.selectById(medical.getPayee()).getName());
			String classString = "";
			if (medical.getItemType() == 1 ) {
				classString = "その他（交通費）";
			} else {
				classString += (medical.getClass1() != null && medical.getClass1() == 1) ? "医療費 " : "";
				classString += (medical.getClass2() != null && medical.getClass2() == 1) ? "医薬品 " : "";
				classString += (medical.getClass3() != null && medical.getClass3() == 1) ? "介護サ " : "";
				classString += (medical.getClass4() != null && medical.getClass4() == 1) ? "その他" : "";
			}
			medical.setClassAll(classString);
			if (medical.getDescription() == null) {medical.setDescription("－");};
		}
		return medicalList;
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
		System.out.println("MedicalServiceImpl::getMedicalListByPage() start.");
		int offset = numPerPage * (page - 1);
		List<Medical> medicalList = medicalDao.selectLimited(offset, numPerPage);
		for (Medical medical : medicalList) {
			System.out.println("MedicalServiceImpl::getMedicalListByPage dump\n"
					+ "\tid: " + medical.getId()
					+ "\n\tmember: " + medical.getMember()
					+ "\n\tpayee: " + medical.getPayee()
					+ "\n\tamount: " + medical.getAmount()
					+ "\n\tdateUse: " + medical.getDateUse()
					+ "\n\titemType: " + medical.getItemType()
					+ "\n\tclass1: " + medical.getClass1()
					+ "\n\tclass2: " + medical.getClass2()
					+ "\n\tclass3: " + medical.getClass3()
					+ "\n\tclass4: " + medical.getClass4()
					+ "\n\tdescription: " + medical.getDescription()
					);

			medical.setMemberName(memberDao.selectById(medical.getMember()).getName());
			medical.setPayeeName(payeeDao.selectById(medical.getPayee()).getName());
			String classString = "";
			if (medical.getItemType() == 1 ) {
				classString = "その他（交通費）";
			} else {
				classString += (medical.getClass1() != null && medical.getClass1() == 1) ? "医療費 " : "";
				classString += (medical.getClass2() != null && medical.getClass2() == 1) ? "医薬品 " : "";
				classString += (medical.getClass3() != null && medical.getClass3() == 1) ? "介護サ " : "";
				classString += (medical.getClass4() != null && medical.getClass4() == 1) ? "その他" : "";
			}
			medical.setClassAll(classString);
//			if (medical.getDescription() == null) {medical.setDescription("－");};
		}
		return medicalList;
	}
}
