package com.kh.spring.board.model.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true) // mybatis는 기본생성자와 setter를 이용한다.
@NoArgsConstructor
public class BoardExt extends Board{

	private boolean hasAttachment;	
	private List<Attachment> attachList;

	
}
