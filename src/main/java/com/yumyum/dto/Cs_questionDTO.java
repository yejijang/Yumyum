package com.yumyum.dto;

public class Cs_questionDTO {

	private String seq;
	private String title;
	private String content;
	private String cs_subject_seq;
	
	private String searchflag;//검색내용인지 아닌지 확인하는 flag
	
	
	
	public String getSearchflag() {
		return searchflag;
	}
	public void setSearchflag(String searchflag) {
		this.searchflag = searchflag;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCs_subject_seq() {
		return cs_subject_seq;
	}
	public void setCs_subject_seq(String cs_subject_seq) {
		this.cs_subject_seq = cs_subject_seq;
	}
	
	
	
	
}
/* 13. 고객센터_자주묻는질문 
CREATE TABLE CS_QUESTION (
	SEQ NUMBER PRIMARY KEY,  시퀀스 
	TITLE VARCHAR2(50) NOT NULL,  제목 
	CONTENT VARCHAR2(1000) NOT NULL,  본문
	CS_SUBJECT_SEQ NUMBER NOT NULL, 고객센터_질문구분 시퀀스 
    CONSTRAINT FK_CSQ_CSS FOREIGN KEY(CS_SUBJECT_SEQ) REFERENCES CS_SUBJECT (SEQ)
);*/