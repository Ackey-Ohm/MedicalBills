package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.MedicalService;



@Controller
@RequestMapping("/medical")
public class MedicalController {

	@Autowired
	MedicalService medicalService;

	@InitBinder
	public void initBinderForm(WebDataBinder binder) {
		var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(dateFormat, true));
	}

	@GetMapping
	public String list(
			@RequestParam(name = "status", defaultValue = "") String status,
			Model model) throws Exception {
		if(status.equals("add")) {
			model.addAttribute("statusMessage", "お知らせを追加しました");
		}

		model.addAttribute("newsList", newsService.getNewsList());
		return "medicle/list";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("news", newsService.getNewsById(id));
		return "news/detail";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		model.addAttribute("memberTypeList", memberService.getTypeList());
		model.addAttribute("newsForm", new NewsForm());
		return "news/save";
	}

	@PostMapping("/add")
	public String addPost(
			HttpSession session,
			@Valid NewsForm newsForm,
			Errors errors,
			Model model) throws Exception {
		// バリデーション
		MultipartFile upfile = newsForm.getUpfile();
		if(!upfile.isEmpty()) {
			// 画像か否か判定する
			String type = upfile.getContentType();
			if(!type.startsWith("image/")) {
				// 画像ではない場合、エラーメッセージを表示
				errors.rejectValue("upfile", "error.not_image_file");
			}
		}

		if(errors.hasErrors()) {
			model.addAttribute("memberTypeList", memberService.getTypeList());
			return "news/save";
		}

		// 投稿者名は管理者のログインIDとする
		newsForm.setAuthor((String) session.getAttribute("loginId"));

		// データベースへ追加
		newsService.addNews(newsForm);
		return "redirect:/news?status=add";
	}

}
