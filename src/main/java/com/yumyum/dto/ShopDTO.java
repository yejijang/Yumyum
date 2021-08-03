package com.yumyum.dto;

public class ShopDTO {
	
	private String seq;
	private String category_seq;
	private String users_seq;
	private String name;
	private String phone;
	private String address;
	private String registration;
	private String min_price;
	private String file1;
	private String file2;
	private String regdate;
	private String auth;
	private String state;
	private String explanation;
	private String picture;
	private String tip;
	private String delivery_state;
	private String take_state;
	
	private String category;
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCategory_seq() {
		return category_seq;
	}
	public void setCategory_seq(String category_seq) {
		this.category_seq = category_seq;
	}
	public String getUsers_seq() {
		return users_seq;
	}
	public void setUsers_seq(String users_seq) {
		this.users_seq = users_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getMin_price() {
		return min_price;
	}
	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getDelivery_state() {
		return delivery_state;
	}
	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}
	public String getTake_state() {
		return take_state;
	}
	public void setTake_state(String take_state) {
		this.take_state = take_state;
	}
	
	

}
/* 2. 가게 */
/*
 * CREATE TABLE SHOP ( SEQ NUMBER PRIMARY KEY, 가게번호 
 * USERS_SEQ NUMBER NOT NULL,사용자번호 
 * CATEGORY_SEQ NUMBER NOT NULL, 카테고리번호
 *  NAME VARCHAR2(50) NOT NULL, 가게이름
 * PHONE VARCHAR2(15) NOT NULL, 가게전화번호 
 * ADDRESS VARCHAR2(100) NOT NULL, 가게주소
 * REGISTRATION NUMBER NOT NULL, 사업자등록번호 
 * MIN_PRICE NUMBER, 최소주문금액 
 * FILE1 VARCHAR2(800) NOT NULL, 첨부파일1(사업자등록증) 
 * FILE2 VARCHAR2(800) NOT NULL, 첨부파일2(통장사본) 
 * REGDATE DATE NOT NULL, 등록날짜 
 * AUTH CHAR(1) DEFAULT 'R' NOT NULL, 승인상태 
 * STATE CHAR(1) DEFAULT 'N' NOT NULL, 영업상태 
 * EXPLANATION VARCHAR2(1000), 가게설명 
 * PICTURE VARCHAR2(800), 외관사진 
 * TIP NUMBER, 기본배달팁 
 * DELIVERY_STATE CHAR(1) , 배달가능여부 
 * TAKE_STATE CHAR(1) , 포장가능여부 
 * CONSTRAINT CK_SHOP_AUTH CHECK (AUTH IN('Y', 'N', 'R')), 
 * CONSTRAINT CK_STATE CHECK (STATE IN ('Y', 'N')), 
 * CONSTRAINT CK_DELIVERY_STATE CHECK (DELIVERY_STATE IN ('Y', 'N')), 
 * CONSTRAINT CK_TAKE_STATE CHECK (TAKE_STATE IN ('Y', 'N')), 
 * CONSTRAINT FK_SHOP_USERS FOREIGN KEY(USERS_SEQ) REFERENCES USERS (SEQ), 
 * CONSTRAINT FK_SHOP_CATEGORY FOREIGN KEY(CATEGORY_SEQ) REFERENCES CATEGORY (SEQ) );
 */