package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.PayeeDao;
import com.example.demo.dao.TotalDao;
import com.example.demo.domain.Member;
import com.example.demo.domain.Payee;
import com.example.demo.domain.Total;

@Service
public class TotalServiceImpl implements TotalService {

	@Autowired
	TotalDao totalDao;
	@Autowired
	MemberDao memberDao;
	@Autowired
	PayeeDao payeeDao;

	@Override
	public Total getTotalById(Integer id, Integer payee) throws Exception{
		Total total = totalDao.totalByName(id, payee);
		return total;
	}

	@Override
	public List<Total> getTotalAll() throws Exception{
		System.out.println("TotalServiceImpl::getTotalAll() start.");
		List<Member> idList = memberDao.selectAll();
		List<Payee> payeeList = payeeDao.selectAll();
		List<Total> totalList = new ArrayList<Total>();

		for (Member member : idList) {					// 各メンバーごとに集計
			System.out.println("TotalServiceImpl::getTotalAll() membr=("
							+ member.getId()+ ")" + member.getName());
			if (member.getTarget() != 0) {				// 集計対象のメンバーであること
				System.out.println("TotalServiceImpl::getTotalAll() isTarget? true.");

				for (Payee payee : payeeList) {			// かつ支払先ごとの集計
					if (payee.getItemType() == 0) {		// まず、医療機関
						System.out.println("TotalServiceImpl::getTotalAll() payee:1=("
								+ payee.getId()+ ")" + payee.getName());
						Total total = totalDao.totalByName(member.getId(), payee.getId());
						if (total != null) {
							System.out.println("TotalServiceImpl::getTotalAll() amount = "
									+ total.getAmount());
							if (/*total.getAmount() != null &&*/ total.getAmount() != 0) {
								totalList.add(total);
								System.out.println("TotalServiceImpl::getTotalAll() totalList.add()");
							}
						}
					}
					if (payee.getRelation() != null && payee.getRelation() != 0) { // 次に交通費
						System.out.println("TotalServiceImpl::getTotalAll() payee:2=("
								+ payee.getRelation()+ ")" + payeeDao.selectById(payee.getRelation()).getName());
						Total travelTotal = totalDao.totalByName(member.getId(), payee.getRelation());
						if (travelTotal != null) {
							System.out.println("TotalServiceImpl::getTotalAll() amount = "
								+ travelTotal.getAmount());
							if (/*travelTotal.getAmount() != null &&*/ travelTotal.getAmount() != 0) {
								totalList.add(travelTotal);
								System.out.println("TotalServiceImpl::getTotalAll() totalList.add()");
							}
						}
					}
				}
			} else {
				System.out.println("TotalServiceImpl::getTotalAll() isTarget? false.");
			}
		}
		System.out.println("TotalServiceImpl::getTotalAll() done.");
		return totalList;
	}
}
