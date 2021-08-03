package com.yumyum.dto;

public class Review_commentDTO {
	
	private String seq;
	private String review_seq;
	private String regdate;
	private String content;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getReview_seq() {
		return review_seq;
	}
	public void setReview_seq(String review_seq) {
		this.review_seq = review_seq;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}

/* 4-3. 리뷰답변 
CREATE TABLE REVIEW_COMMENT (
	SEQ NUMBER PRIMARY KEY, 시퀀스 
	REVIEW_SEQ NUMBER NOT NULL, 리뷰 시퀀스 
	REGDATE DATE DEFAULT SYSDATE NOT NULL, 등록일시 
	CONTENT VARCHAR2(1000) NOT NULL, 답글내용 
    CONSTRAINT FK_RCOMMENT_REVIEW FOREIGN KEY(REVIEW_SEQ) REFERENCES REVIEW(SEQ) ON DELETE CASCADE
);*/