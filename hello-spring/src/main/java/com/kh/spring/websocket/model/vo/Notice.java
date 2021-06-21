package com.kh.spring.websocket.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {
	private String from;
	private String to;
	private String message;
	private String type;
	private long time;
}
