package com.example.demo.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Medical;
import com.example.demo.service.MedicalService;

@Controller
@RequestMapping("/medical")
public class MedicalController {

	// 1ページ当たりの表示件数
	private static final int NUM_PER_PAGE = 5;

	@Autowired
	MedicalService service;

	@GetMapping
	public String list(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "status", required = false) String status,
			Model model) throws Exception {
		System.out.println("MedicalController::list() start.");
		model.addAttribute("medical", service.getMedicalListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		model.addAttribute("statusMessage", getStatusMessage(status));
		return "medical/list";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		System.out.println("MedicalController::addGet() start.");
		model.addAttribute("title", "受診者名の追加");
		Medical medical = new Medical();
		medical.setAmount(0);
		model.addAttribute("medical", medical);
		return "medical/save";
	}

	@PostMapping("/add")
	public String addPost(
			@Valid Medical medical,
			Errors errors,
			Model model) throws Exception {
		System.out.println("MedicalController::addPost() start.");
		if(errors.hasErrors()) {
			System.out.println("MedicalController::hasErrors(): " + errors.toString());
			model.addAttribute("title", "医療費の追加");
			return "medical/save";
		}

		service.addMedical(medical);
		return "redirect:/medical?status=add";
	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		System.out.println("MedicalController::editGet() start.");
		model.addAttribute("title", "医療費情報の変更");
		model.addAttribute("medical", service.getMedicalById(id));
		return "medical/save";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			@PathVariable Integer id,
			@Valid Medical medical,
			Errors errors,
			Model model) throws Exception {
		System.out.println("MedicalController::editPost() start.");
		if(errors.hasErrors()) {
			System.out.println("MedicalController::editPost() hasErrors: " + errors.toString());
			model.addAttribute("title", "受診者情報の変更");
			return "members/save";
		}

		medical.setId(id);
		service.editMedical(medical);
		return "redirect:/medical?status=edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) throws Exception {
		System.out.println("MedicalController::delete() start.");
		service.deleteMedical(id);
		return "redirect:/medical?status=delete";
	}

	private String getStatusMessage(String status) {
		String message = null;

		if(status == null) {
			System.out.println("MedicalController::getStatusMessage() nothing.");
			return message;
		}

		switch(status) {
			case "add":
				message = "医療費を追加しました。";
				break;
			case "edit":
				message = "医療費情報を更新しました。";
				break;
			case "delete":
				message = "医療費情報を削除しました。";
				break;
		}
		System.out.println("MedicalController::getStatusMessage() done : " + message);
		return message;
	}

}

