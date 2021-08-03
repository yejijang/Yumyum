package com.yumyum.dto;

public class MenuDTO {
	
	private String seq;
	private String menu_group_seq;
	private String name;
	private String price;
	private String picture;
	private String explanation;
	private String state;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMenu_group_seq() {
		return menu_group_seq;
	}
	public void setMenu_group_seq(String menu_group_seq) {
		this.menu_group_seq = menu_group_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
}
/* 3-1. 메뉴
CREATE TABLE MENU (
	SEQ NUMBER PRIMARY KEY, 메뉴번호 
	MENU_GROUP_SEQ NUMBER NOT NULL,  메뉴그룹번호 
	NAME VARCHAR2(50) NOT NULL,  메뉴명 
	PRICE NUMBER NOT NULL,  메뉴가격 
	PICTURE VARCHAR2(800),  메뉴사진 
	EXPLANATION VARCHAR2(1000) NOT NULL, 메뉴설명
	STATE CHAR(1) NOT NULL, 주문가능여부
    CONSTRAINT CK_MSTATE CHECK (STATE IN ('Y', 'N')),
    CONSTRAINT FK_MENU_MGR FOREIGN KEY(MENU_GROUP_SEQ) REFERENCES MENU_GROUP (SEQ) ON DELETE CASCADE
);*/