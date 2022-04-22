package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.MemberDao;
import com.example.demo.domain.Member;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public List<Member> getMemberList() throws Exception {
		return memberDao.selectAll();
	}

	@Override
	public Member getMemberById(Integer id) throws Exception {
		return memberDao.selectById(id);
	}

	@Override
	public void addMember(Member member) throws Exception {
		memberDao.insert(member);
	}

	@Override
	public void editMember(Member member) throws Exception {
		memberDao.update(member);
	}

	@Override
	public void deleteMember(Integer id) throws Exception {
		memberDao.delete(id);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		long totalNum = memberDao.count();
		totalNum = (totalNum * 2) / numPerPage + 1;
		return (int) totalNum / 2;
	}

	@Override
	public List<Member> getMemberListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return memberDao.selectLimited(offset, numPerPage);
	}
}
