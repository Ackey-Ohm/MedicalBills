package com.example.demo.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Member {

	private Integer id;

	@NotBlank
	@Size(max=30)
	private String name;	// 受診者名

	@Range(min=0, max=1)
	private Integer target;		// 対象の識別 0:無効 1:有効
}
