package com.spring.javaProjectS.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Range;

import lombok.Data;

//  public @Data class  UserVO {
@Data	
public  class  UserVO {
	private int idx;
	
	//@NotEmpty()
	// 공백을 체크하는 annotation , validator랑 한 쌍
	// hiber-nate 의존성 주입 후 사용 가능
	@NotEmpty(message = "아이디가 공백입니다./midEmpty")
	@Size(min=3, max=20, message="아이디 길이가 잘못되었습니다./midSizeNo")
	private String mid;
	
	@NotEmpty(message = "성명이 공백입니다./nameEmpty")
	@Size(min=3, max=20, message="성명의 길이가 잘못되었습니다./nameSizeNo")
	private String name;
	
	@Range(min=19, max=99, message="나이의 범위를 벗어났습니다./ageRangeNo")
	private int age;
	
	private String address;
}
