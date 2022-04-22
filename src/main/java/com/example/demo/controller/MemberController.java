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

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

	// 1ページ当たりの表示人数
	private static final int NUM_PER_PAGE = 5;

	@Autowired
	MemberService service;

	@GetMapping
	public String list(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "status", required = false) String status,
			Model model) throws Exception {
		model.addAttribute("members", service.getMemberListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		model.addAttribute("statusMessage", getStatusMessage(status));
		return "members/list";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		model.addAttribute("title", "受診者名の追加");
		model.addAttribute("member", new Member());
		return "members/save";
	}

	@PostMapping("/add")
	public String addPost(
			@Valid Member member,
			Errors errors,
			Model model) throws Exception {
		if(errors.hasErrors()) {
			model.addAttribute("title", "受診者の追加");
			return "members/save";
		}

		service.addMember(member);
		return "redirect:/members?status=add";
	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("title", "受診者情報の変更");
		model.addAttribute("member", service.getMemberById(id));
		return "members/save";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			@PathVariable Integer id,
			@Valid Member member,
			Errors errors,
			Model model) throws Exception {
		if(errors.hasErrors()) {
			model.addAttribute("title", "受診者情報の変更");
			return "members/save";
		}

		member.setId(id);
		service.editMember(member);
		return "redirect:/members?status=edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) throws Exception {
		service.deleteMember(id);
		return "redirect:/members?status=delete";
	}

	private String getStatusMessage(String status) {
		String message = null;

		if(status == null) {
			return message;
		}

		switch(status) {
			case "add":
				message = "受診者を追加しました。";
				break;
			case "edit":
				message = "受診者情報を更新しました。";
				break;
			case "delete":
				message = "受診者を削除しました。";
				break;
		}
		return message;
	}

}
