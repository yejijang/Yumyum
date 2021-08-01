package com.yumyum.dto;

public class OrderlistDTO {

	private String seq;
	private String users_seq;
	private String shop_seq;
	private String order_num;
	private String status;
	private String regdate;
	private String cleardate;
	private String division;
	private String request_owner;
	private String request_rider;
	private String order_price;
	private String charge_price;
	private String charge_method;
	private String use_points;
	private String customer_coupon_seq;
	private String delivery_address;
	private String cancel_reason_seq;
	
	private String name; //카테고리명(마이페이지)
	private String cnt; //카테고리별 주문 수
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getUsers_seq() {
		return users_seq;
	}
	public void setUsers_seq(String users_seq) {
		this.users_seq = users_seq;
	}
	public String getShop_seq() {
		return shop_seq;
	}
	public void setShop_seq(String shop_seq) {
		this.shop_seq = shop_seq;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getCleardate() {
		return cleardate;
	}
	public void setCleardate(String cleardate) {
		this.cleardate = cleardate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getRequest_owner() {
		return request_owner;
	}
	public void setRequest_owner(String request_owner) {
		this.request_owner = request_owner;
	}
	public String getRequest_rider() {
		return request_rider;
	}
	public void setRequest_rider(String request_rider) {
		this.request_rider = request_rider;
	}
	public String getOrder_price() {
		return order_price;
	}
	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}
	public String getCharge_price() {
		return charge_price;
	}
	public void setCharge_price(String charge_price) {
		this.charge_price = charge_price;
	}
	public String getCharge_method() {
		return charge_method;
	}
	public void setCharge_method(String charge_method) {
		this.charge_method = charge_method;
	}
	public String getUse_points() {
		return use_points;
	}
	public void setUse_points(String use_points) {
		this.use_points = use_points;
	}
	public String getCustomer_coupon_seq() {
		return customer_coupon_seq;
	}
	public void setCustomer_coupon_seq(String customer_coupon_seq) {
		this.customer_coupon_seq = customer_coupon_seq;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getCancel_reason_seq() {
		return cancel_reason_seq;
	}
	public void setCancel_reason_seq(String cancel_reason_seq) {
		this.cancel_reason_seq = cancel_reason_seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
}
