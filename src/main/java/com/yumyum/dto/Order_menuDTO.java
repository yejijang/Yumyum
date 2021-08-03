package com.yumyum.dto;

public class Order_menuDTO {

	private String seq;
	private String orderlist_seq;
	private String menu_seq;
	private String menu_option_seq;
	private String cnt;

	private String menu_name; // 메뉴명(주문내역상세보기)
	private String menu_picture; // 메뉴사진
	private String option_name; // 옵션명

	private String review_seq; //리뷰번호


	public String getReview_seq() {
		return review_seq;
	}

	public void setReview_seq(String review_seq) {
		this.review_seq = review_seq;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrderlist_seq() {
		return orderlist_seq;
	}

	public void setOrderlist_seq(String orderlist_seq) {
		this.orderlist_seq = orderlist_seq;
	}

	public String getMenu_seq() {
		return menu_seq;
	}

	public void setMenu_seq(String menu_seq) {
		this.menu_seq = menu_seq;
	}

	public String getMenu_option_seq() {
		return menu_option_seq;
	}

	public void setMenu_option_seq(String menu_option_seq) {
		this.menu_option_seq = menu_option_seq;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_picture() {
		return menu_picture;
	}

	public void setMenu_picture(String menu_picture) {
		this.menu_picture = menu_picture;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

}