package com.kh.spring.board.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@ToString(callSuper = true)
@NoArgsConstructor
public class BoardExt extends Board{

	private boolean hasAttachment;

	public BoardExt(
			int no, String title, String memberId, 
			String content, Date regDate, int readCount,
			boolean hasAttachment) {
		super(no, title, memberId, content, regDate, readCount);
		this.hasAttachment = hasAttachment;
	}
	
	
	
}
