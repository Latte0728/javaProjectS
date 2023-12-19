package com.spring.javaProjectS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.javaProjectS.service.BoardService;
import com.spring.javaProjectS.vo.BoardVO;

@Controller
@RequestMapping("/board")
// 중간 경로 지정
public class BoardController {

	@Autowired
	// BoardService 에서 Controller를 부름
	BoardService boardService;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardListGet(Model model) {
		List<BoardVO> vos = boardService.getBoardList();
		
		model.addAttribute("vos", vos);
		// model 객체를 이용하여 vos 값을 "vos" 변수에 생성하여 담는다.
		return "board/boardList";
	}
	
	@RequestMapping(value = "/boardInput", method = RequestMethod.GET)
	public String boardInputGet() {
		return "board/boardInput";
	}
	
	@RequestMapping(value = "/boardInput", method = RequestMethod.POST)
	public String boardInputPost(BoardVO vo) {
		int res = boardService.setBoardInput(vo);
		
		if(res == 1) return "redirect:/message/boardInputOk";
		else return "redirect:/message/boardInputNo";
	}
	@RequestMapping(value = "/boardContent", method = RequestMethod.GET)
	public String boardContentGet(int idx, Model model) {
		BoardVO vo = boardService.getBoardContent(idx);
		
		model.addAttribute("vo", vo);
		return "board/boardContent";
	}
}

