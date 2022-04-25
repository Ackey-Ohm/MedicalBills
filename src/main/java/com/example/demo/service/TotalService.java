package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Total;

@Service
public interface TotalService {

	Total getTotalById(Integer id, Integer payee, Integer itemType) throws Exception;

	List<Total> getTotalAll() throws Exception;

}
