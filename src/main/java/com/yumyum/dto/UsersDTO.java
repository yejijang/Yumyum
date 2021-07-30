package com.yumyum.dto;

public class UsersDTO {

	private String seq;
	private String nickname;
	private String name;
	private String id;
	private String password;
	private String email;
	private String phone;
	private String auth;
	private String point;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	/*
	 * CREATE TABLE USERS ( SEQ NUMBER PRIMARY KEY, -- 사용자번호 NICKNAME VARCHAR2(30),
	 * -- 닉네임 NAME VARCHAR2(15) NOT NULL, -- 이름 ID VARCHAR2(30) NOT NULL, -- 아이디
	 * EMAIL VARCHAR2(50) NOT NULL, -- 이메일 PASSWORD VARCHAR2(30) NOT NULL, -- 비밀번호
	 * PHONE VARCHAR2(15) NOT NULL, -- 휴대폰번호 AUTH CHAR(1) NOT NULL, -- 권한 POINT
	 * NUMBER DEFAULT 0, -- 포인트 CONSTRAINT CK_USERS_AUTH CHECK (AUTH IN ('C', 'O',
	 * 'A')) --C:소비자, O:점주, A:관리자 );
	 */
}
