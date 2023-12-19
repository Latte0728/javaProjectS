package com.spring.javaProjectS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javaProjectS.dao.BoardDAO;
import com.spring.javaProjectS.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList() {
		return boardDAO.getBoardList();
		// getBoardList(BoardList.jsp에 관련된 업무); 메소드()를 이용하여 boardDAO에게 패스!!
	}

	@Override
	public int setBoardInput(BoardVO vo) {
		return boardDAO.setBoardInput(vo);
	}

	@Override
	public BoardVO getBoardContent(int idx) {
		return boardDAO.getBoardContent(idx);
	}
	
}

