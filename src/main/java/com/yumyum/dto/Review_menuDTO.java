package com.yumyum.dto;

public class Review_menuDTO {

	private String seq;
	private String review_notice_seq;
	private String name;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getReview_notice_seq() {
		return review_notice_seq;
	}
	public void setReview_notice_seq(String review_notice_seq) {
		this.review_notice_seq = review_notice_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	  
}

/*
 4-2. 서비스품목 
CREATE TABLE REVIEW_MENU (
	SEQ NUMBER PRIMARY KEY, 시퀀스
	REVIEW_NOTICE_SEQ NUMBER NOT NULL, 리뷰공지시퀀스
	NAME VARCHAR2(50) NOT NULL,  서비스품목명 
    CONSTRAINT FK_RMENU_RNOTICE FOREIGN KEY(REVIEW_NOTICE_SEQ) REFERENCES REVIEW_NOTICE(SEQ)
);
 */
