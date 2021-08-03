package com.yumyum.dto;

public class ReviewDTO {
	
	private String seq;
	private String shop_seq;
	private String orderlist_seq;
	private String score;
	private String picture;
	private String content;
	private String regdate;
	private String recommend;
	private String nonrecommend;
	
	private String nickname;
	private String avg;
	
	private String user_seq; //리뷰에서 고객이 주문한 메뉴를 보여주기위해 필요
	
	
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public String getOrderlist_seq() {
		return orderlist_seq;
	}
	public void setOrderlist_seq(String orderlist_seq) {
		this.orderlist_seq = orderlist_seq;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getNonrecommend() {
		return nonrecommend;
	}
	public void setNonrecommend(String nonrecommend) {
		this.nonrecommend = nonrecommend;
	}
	
	

}
/* 4. 리뷰 
CREATE TABLE REVIEW (
	SEQ NUMBER PRIMARY KEY,  시퀀스 
	SHOP_SEQ NUMBER NOT NULL,  가게 시퀀스 
	ORDERLIST_SEQ NUMBER NOT NULL,  주문내역 시퀀스 
	SCORE NUMBER NOT NULL,  별점 
	PICTURE VARCHAR2(800), 리뷰이미지(이미지 파일+경로) 
	CONTENT VARCHAR2(1000) NOT NULL,  리뷰내용 
	REGDATE DATE DEFAULT SYSDATE NOT NULL,  등록일시 
	RECOMMEND NUMBER DEFAULT 0,  추천 
	NONRECOMMEND NUMBER DEFAULT 0, 비추천 
    CONSTRAINT CK_SCORE CHECK (SCORE BETWEEN 0 AND 5),
    CONSTRAINT FK_REVIEW_SHOP FOREIGN KEY(SHOP_SEQ) REFERENCES SHOP(SEQ),
    CONSTRAINT FK_REVIEW_ORDER FOREIGN KEY(ORDERLIST_SEQ) REFERENCES ORDERLIST(SEQ)
);*/