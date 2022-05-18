package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.CommentDto;

public interface CommentService {
	public List<BoardDto> retrieveBoard();
	public List<CommentDto> retrieveComment(int articleno);
	public boolean writeBoard(CommentDto comment);
	public boolean updateBoard(BoardDto board);
	public boolean deleteBoard(int articleno);
}
