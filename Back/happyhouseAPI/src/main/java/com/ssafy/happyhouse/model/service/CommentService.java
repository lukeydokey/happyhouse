package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.CommentDto;

public interface CommentService {
	public List<CommentDto> retrieveComment(int articleno);
	public boolean writeComment(CommentDto comment);
	public boolean deleteComment(int commentno);
}
