package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Total;

@Mapper
public interface TotalDao {
	Total totalByName(@Param("name") Integer name
					, @Param("payee") Integer payee
					) throws Exception;

}