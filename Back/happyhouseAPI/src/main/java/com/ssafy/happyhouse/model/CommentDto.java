package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comment (댓글)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class CommentDto {
	@ApiModelProperty(value = "글번호")
	private int commentno;
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public CommentDto(int commentno, int articleno, String userid, String content, String regtime) {
		super();
		this.commentno = commentno;
		this.articleno = articleno;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "CommentDto [commentno=" + commentno + ", articleno=" + articleno + ", userid=" + userid + ", content="
				+ content + ", regtime=" + regtime + "]";
	}

	

}