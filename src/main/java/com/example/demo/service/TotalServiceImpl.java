package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.TotalDao;
import com.example.demo.domain.Payee;
import com.example.demo.domain.Total;
public class TotalServiceImpl implements TotalService {

	@Autowired
	TotalDao totalDao;
	@Autowired
	MemberDao memberDao;
	@Autowired
	Payee PayeeDao;

	@Override
	public Total getTotalById(Integer id, Integer payee, Integer itemType) throws Exception{
		Total total = totalDao.totalByName(id, payee, itemType);
		return total;
	}

	@Override
	public List<Total> getTotalAll() throws Exception{
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> payeeList = new ArrayList<Integer>();
		Integer itemType = 1;
		List<Total> totalList = new ArrayList<Total>();

		for (Integer id : idList) {
			for (Integer payee : payeeList) {
				Total total = totalDao.totalByName(id, payee, 1);
				totalList.add(total);
				Total travelTotal = totalDao.totalByName(id, payee, 2);
				totalList.add(total);
			}
		}
		return totalList;
	}


}
