package com.yumyum.dto;

public class Review_noticeDTO {

	private String seq;
	private String shop_seq;
	private String content;
	private String start_date;
	private String end_date;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getShop_seq() {
		return shop_seq;
	}
	public void setShop_seq(String shop_seq) {
		this.shop_seq = shop_seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
}

/*
 * 4-1. 리뷰공지 
 * CREATE TABLE REVIEW_NOTICE ( 
 * SEQ NUMBER PRIMARY KEY, 시퀀스 
 * SHOP_SEQ NUMBER NOT NULL, 가게 시퀀스 
 * CONTENT VARCHAR2(1000) NOT NULL, 이벤트 설명 
 * START_DATE DATE NOT NULL, 시작날짜 
 * END_DATE DATE NOT NULL, 종료날짜 
 * CONSTRAINT FK_RNOTICE_SHOP FOREIGN KEY(SHOP_SEQ) REFERENCES SHOP(SEQ) );
 */