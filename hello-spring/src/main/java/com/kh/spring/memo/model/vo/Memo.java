package com.kh.spring.memo.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든생성자
public class Memo {

	private int no;
	private String memo;
	private Date regDate;
	
}
