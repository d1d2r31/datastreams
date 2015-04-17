package com.datastreams.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastreams.project.dao.BoardDAO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;

	public Object boardList() {
		
		return boardDAO.boardList();
	}
}
