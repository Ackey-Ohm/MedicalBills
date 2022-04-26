package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.TotalService;

@Controller
@RequestMapping("/total")
public class TotalController {

	@Autowired
	TotalService service;

	@GetMapping("/list")
	public String list(
			Model model) throws Exception {
		System.out.println("TotalController::list() start.");
		model.addAttribute("toalList", service.getTotalAll());
		return "total/list";
	}


}
