package com.example.demo.domain;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Total {
	private Integer id;

	@NotNull
	private Integer member;		// 受診者コード
	private String memberName;	// 受診者名

	@NotNull
	private Integer payee;		// 支払先コード
	private String payeeName;	// 支払先名称

	@NotNull
	private Integer itemType;	// 1:医療費 2:交通費
	private Integer class1;		// 1:診療、診察
	private Integer class2;		// 1:医薬品購入
	private Integer class3;		// 1:介護保険サービス
	private Integer class4;		// 1:その他
	private String classAll;	// 区分文字列 交通費 : 診療／医薬品／介護サ／その他

	@NotNull
	private Integer amount;		// 支払金額合計

}
