package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
    @Autowired
	private CommentMapper commentMapper;

    @Override
	public List<BoardDto> retrieveBoard() {
		return commentMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(CommentDto comment) {
		return commentMapper.insertBoard(comment) == 1;
	}

	@Override
	public List<CommentDto> retrieveComment(int articleno) {
		return commentMapper.selectCommentByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(BoardDto board) {
		return commentMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return commentMapper.deleteBoard(articleno) == 1;
	}
}