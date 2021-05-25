package com.kh.spring.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.util.HelloSpringUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	@Autowired
	private ServletContext application;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList.do")
	public String boardList(@RequestParam(required = true, defaultValue = "1") int cpage, Model model) {
		try {
			log.debug("boardService = {}", boardService.getClass());
			log.debug("cpage = {}", cpage);
			final int limit = 10;
			final int offset = (cpage - 1) * limit;
			Map<String, Object> param = new HashMap<>();
			param.put("limit", limit);
			param.put("offset", offset);
			
			List<Board> list = boardService.selectBoardList(param);
			model.addAttribute("list", list);
			
			log.debug("list = {}", list);
		} catch(Exception e){
			log.error("게시글 조회 오류!", e);
			throw e;
		}
		return "board/boardList";
	}
	
	@GetMapping("/boardForm.do")
	public void boardForm() {
		
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(
						@ModelAttribute Board board,
						@RequestParam(name = "upFile") MultipartFile[] upFiles
					) throws Exception {
		log.debug("board = {}", board);
//		for(MultipartFile upFile : upFiles) {
//			log.debug("upFile = {}", upFile);
//			log.debug("upFile.name = {}", upFile.getOriginalFilename());
//			log.debug("upFile.size = {}", upFile.getSize());
//			log.debug("-----------------------------------------------------");			
//		}
		
		//1. 파일 저장 : 절대경로 /resource/upload/board
		//pageContext - request - session - application
		String saveDirectory = application.getRealPath("/resource/upload/board");
		log.debug("saveDirectory = {}", saveDirectory);
		
		//디렉토리 생성
		File dir = new File(saveDirectory);
		if(!dir.exists())
			dir.mkdirs(); // 복수개의 디렉토리를 생성
		
		List<Attachment> attachList = new ArrayList<>();
		
		for(MultipartFile upFile : upFiles) {
			//input[name=upfile]로부터 비어있는 upFile이 넘어온다.
			if(upFile.isEmpty()) continue;
			
			String renamedFilename = 
					HelloSpringUtils.getRenamedFilename(upFile.getOriginalFilename());
			
			//a.서버컴퓨터에 저장
			File dest = new File(saveDirectory, renamedFilename);
			upFile.transferTo(dest); // 파일이동
			
			//b.저장된 데이터를 Attachment객체에 저장 및 list에 추가
			Attachment attach = new Attachment();
			attach.setOriginalFilename(upFile.getOriginalFilename());
			attach.setRenamedFilename(renamedFilename);
			attachList.add(attach);
			
		}
		
		log.debug("attachList = {}", attachList);
		
		
		//2. 업무로직 : db저장 board, attachment
		
		//3. 사용자피드백 & 리다이렉트
		
		return "redirect:/board/boardList.do";
	}
}
