package com.yumyum.dto;

public class CartDTO {

	private String seq;
	private String users_seq;
	private String menu_seq;
	private String menu_option_seq;
	private int cnt;

	private String menu_name; // 메뉴명(장바구니내역)
	private int menu_price; // 메뉴가격
	private String shop_seq; // 가게명
	private String menu_option_name; // 메뉴추가옵션명
	private String menu_option_price; // 메뉴추가옵션가격

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}

	public String getShop_seq() {
		return shop_seq;
	}

	public void setShop_seq(String shop_seq) {
		this.shop_seq = shop_seq;
	}

	public String getMenu_option_name() {
		return menu_option_name;
	}

	public void setMenu_option_name(String menu_option_name) {
		this.menu_option_name = menu_option_name;
	}

	public String getUsers_seq() {
		return users_seq;
	}

	public void setUsers_seq(String users_seq) {
		this.users_seq = users_seq;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getMenu_option_price() {
		return menu_option_price;
	}

	public void setMenu_option_price(String menu_option_price) {
		this.menu_option_price = menu_option_price;
	}

}