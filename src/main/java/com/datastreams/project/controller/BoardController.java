package com.datastreams.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.datastreams.project.service.BoardService;

@Controller
@RequestMapping("/board")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private ModelAndView mav;
	
	@Autowired
	private BoardService boardService;
	
	//리스트 페이지
	@RequestMapping(value="/boardList", method= RequestMethod.POST)
	public ModelAndView boardList(){
		
		mav= new ModelAndView();
		mav.addObject("boardList", boardService.boardList());
		mav.setViewName("board/boardlist");
		return mav;
		
		
	}
	//상세보기 페이지
	@RequestMapping(value="/boardDetail", method= RequestMethod.POST)
	public ModelAndView boardDetail(){
		
		return mav;
		
	}
	
	//글작성 페이지
	@RequestMapping(value="/boardInsert", method= RequestMethod.POST)
	public ModelAndView boardInsert(){
		
		return mav;
		
	}
	//글삭제 
	@RequestMapping(value="/boardDelete", method= RequestMethod.POST)
	public ModelAndView boardDelete(){
		
		return mav;
		
	}
	@RequestMapping(value="/boardUpdate", method= RequestMethod.POST)
	//글수정페이지
	public ModelAndView boardUpdate(){
		
		return mav;
	}
}
