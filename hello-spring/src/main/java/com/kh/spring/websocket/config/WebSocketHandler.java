package com.kh.spring.websocket.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
	
	/**
	 * multi-thread에서 동기화를 지원하는 list
	 */
	List<WebSocketSession> sessionList = new CopyOnWriteArrayList<>();
	
	/**
	 * websocket연결 성공후 호출
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		log.debug("onopen : {}", sessionList.size(), session);
	}

	/**
	 * client가 message를 전송한 경우 호출
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.debug("onmessage : {} from {}", message, session);
		
		String sender = session.getId();
		TextMessage msg = new TextMessage(sender + " : " + message.getPayload());
		
		for(WebSocketSession sess : sessionList) {
			sess.sendMessage(msg);
		}
		
	}

	
	/**
	 * websocket연결 해제후 호출
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		log.debug("onopen : {}", sessionList.size());
	}
	
	
	
}
