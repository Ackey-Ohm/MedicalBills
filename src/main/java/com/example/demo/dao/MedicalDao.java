package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Medical;

@Mapper
public interface MedicalDao {

	List<Medical> selectAll() throws Exception;
	Medical selectById(Integer id) throws Exception;
	void insert(Medical medical) throws Exception;
	void update(Medical medical) throws Exception;
	void delete(Integer id) throws Exception;

	// ページ分割機能用
	Long count() throws Exception;
	List<Medical> selectLimited(@Param("offset") int offset, @Param("num") int num) throws Exception;
}
