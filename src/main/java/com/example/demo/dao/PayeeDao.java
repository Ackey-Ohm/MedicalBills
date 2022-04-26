package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Payee;

@Mapper
public interface PayeeDao {

	List<Payee> selectAll() throws Exception;
	Payee selectById(Integer id) throws Exception;
	void insert(Payee payee) throws Exception;
	void update(Payee payee) throws Exception;
	void delete(Integer id) throws Exception;

	// ページ分割機能用
	Long count() throws Exception;
	List<Payee> selectLimited(@Param("offset") int offset, @Param("num") int num) throws Exception;

}
