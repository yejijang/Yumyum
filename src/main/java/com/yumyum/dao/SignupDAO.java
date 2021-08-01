package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.UsersDTO;

public class SignupDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public SignupDAO() {

		try {
			conn = DBUtil.open();
		} catch (Exception e) {
			System.out.println("SignupDAO.SignupDAO()");
			e.printStackTrace();
		}
	}

	public int newCustomer(UsersDTO dto) {

		try {

			String sql = "declare\r\n"
						+ "    vseq number;\r\n"
						+ "begin\r\n"
						+ "    vseq := SEQ_USERS.nextVal;\r\n"
						+ "    insert into USERS (SEQ, NICKNAME, NAME, ID, EMAIL, PASSWORD, PHONE, AUTH, POINT) values (vseq, ?, ?, ?, ?, ?, ?, ?, DEFAULT);\r\n"
						+ "    insert into CUSTOMER_COUPON (SEQ, USERS_SEQ, COUPON_SEQ, USE) values (SEQ_CUSTOMER_COUPON.nextVal, vseq, 3, DEFAULT);\r\n"
						+ "end;";
			
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getNickname());
			pstat.setString(2, dto.getName());
			pstat.setString(3, dto.getId());
			pstat.setString(4, dto.getEmail());
			pstat.setString(5, dto.getPassword());
			pstat.setString(6, dto.getPhone());
			pstat.setString(7, dto.getAuth());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SignupDAO.newCustomer()");
			e.printStackTrace();
		}
		return 0;
	}

	public int newOwner(UsersDTO dto) {
		
		try {

			String sql = "declare\r\n"
						+ "    vseq number;\r\n"
						+ "begin\r\n"
						+ "    vseq := SEQ_USERS.nextVal;\r\n"
						+ "    insert into USERS (SEQ, NICKNAME, NAME, ID, EMAIL, PASSWORD, PHONE, AUTH, POINT) values (vseq, null, ?, ?, ?, ?, ?, ?, null);\r\n"
						+ "    insert into CUSTOMER_COUPON (SEQ, USERS_SEQ, COUPON_SEQ, USE) values (SEQ_CUSTOMER_COUPON.nextVal, vseq, 3, DEFAULT);\r\n"
						+ "end;";
			
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getId());
			pstat.setString(3, dto.getEmail());
			pstat.setString(4, dto.getPassword());
			pstat.setString(5, dto.getPhone());
			pstat.setString(6, dto.getAuth());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SignupDAO.newOwner()");
			e.printStackTrace();
		}
		return 0;
	}

}