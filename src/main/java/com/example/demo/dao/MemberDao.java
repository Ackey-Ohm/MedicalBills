package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Member;

@Mapper
public interface MemberDao {

	List<Member> selectAll() throws Exception;
	List<Integer> selectIdAll() throws Exception;
	Member selectById(Integer id) throws Exception;
	void insert(Member member) throws Exception;
	void update(Member member) throws Exception;
	void delete(Integer id) throws Exception;

	// ページ分割機能用
	Long count() throws Exception;
	List<Member> selectLimited(@Param("offset") int offset, @Param("num") int num) throws Exception;

}
