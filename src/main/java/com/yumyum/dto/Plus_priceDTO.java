package com.yumyum.dto;

public class Plus_priceDTO {
	
	private String seq;
	private String shop_seq;
	private String price;
	private String dong;
	
	
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}

}

/*
 * 19. 추가 배달팁 
 * CREATE TABLE PLUS_PRICE ( 
 * SEQ NUMBER PRIMARY KEY, 시퀀스 
 * SHOP_SEQ NUMBER NOT NULL, 가게 시퀀스 
 * PRICE NUMBER NOT NULL,  금액 
   DONG VARCHAR2(100) NOT NULL,  행정동 
 * CONSTRAINT FK_PPRICE_SHOP FOREIGN KEY(SHOP_SEQ) REFERENCES SHOP (SEQ));
 */