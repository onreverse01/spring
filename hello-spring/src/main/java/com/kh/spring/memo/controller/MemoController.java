package com.kh.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	@GetMapping("/memo.do")
	public ModelAndView selectMemoList(ModelAndView mav) {
		log.debug("memoService = {}", memoService.getClass());
		// MemoServiceImpl
		
		//1. 업무로직 : memo목록 조회
		List<Memo> list = memoService.selectMemoList();
		log.debug("list = {}", list);
		//2. jsp에 위임
		mav.addObject("list", list);
		
		mav.setViewName("memo/memo");
		return mav;
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(@ModelAttribute Memo memo, RedirectAttributes redirectAttr) {
		
		try {
			log.debug("memo = {}", memo);
//			log.info("memo = {}",memo);
			//1.업무로직
			int result = memoService.insertMemo(memo);
			//2. 사용자피드백
			redirectAttr.addFlashAttribute("msg", "메모작성성공");
		} catch(Exception e) {
			log.error("메모작성오류!", e);
			throw e;
		}

		return "redirect:/memo/memo.do";
	}
	
}
