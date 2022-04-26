package com.example.demo.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
@Data
public class Payee {
	private Integer id;
	@NotBlank
	@Size(max=30)
	private String name;		// 支払先名

	@Range(min=1, max=2)
	private Integer itemType;	// 対象の識別 1:医療費一般 2:交通費

	private Integer relation;	// 対象の交通費（交通機関）

	private String description;	// 摘要

}
