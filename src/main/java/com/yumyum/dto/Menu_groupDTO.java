package com.yumyum.dto;

public class Menu_groupDTO {
	
	private String seq;
	private String shop_seq;
	private String name;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}

/* 3. 메뉴그룹 
CREATE TABLE MENU_GROUP (
	SEQ NUMBER PRIMARY KEY, 메뉴그룹번호
	SHOP_SEQ NUMBER NOT NULL, 가게번호 
	NAME VARCHAR2(50) NOT NULL , 메뉴그룹명     
    CONSTRAINT FK_MGR_SHOP FOREIGN KEY(SHOP_SEQ) REFERENCES SHOP (SEQ) ON DELETE CASCADE
);*/