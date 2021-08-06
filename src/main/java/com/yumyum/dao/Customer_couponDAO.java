package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Customer_couponDTO;

public class Customer_couponDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Customer_couponDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Customer_couponDAO.Customer_couponDAO()");
			e.printStackTrace();
		}
	}

	//Mypage(마이페이지)에서 seq를 넘겨 받아 사용 가능 쿠폰을 조회한다.
	public ArrayList<Customer_couponDTO> getListCoupon(String seq) {

		try {
			
			String sql = "SELECT ROWNUM AS RNUM\r\n"
					+ "     , X.*\r\n"
					+ "FROM ( SELECT C.NAME, FN_FORMAT_WON(C.PRICE) AS PRICE\r\n"
					+ "        FROM CUSTOMER_COUPON CC\r\n"
					+ "        INNER JOIN COUPON C\r\n"
					+ "        ON CC.COUPON_SEQ = C.SEQ\r\n"
					+ "        WHERE CC.USERS_SEQ = ?\r\n"
					+ "        AND CC.USE = 'N'\r\n"
					+ "     ) X";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Customer_couponDTO> list = new ArrayList<Customer_couponDTO>();
			
			while(rs.next()) {
				
				Customer_couponDTO dto = new Customer_couponDTO();
				dto.setRnum(rs.getString("rnum"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Customer_couponDAO.getListCoupon()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Order(주문페이지)에서 seq를 넘겨 받아 사용 가능 쿠폰을 조회한다.
	public ArrayList<Customer_couponDTO> getOrderCouponList(String seq) {
		
		try {
			
			String sql = "SELECT CC.SEQ, CC.USERS_SEQ, CC.COUPON_SEQ, CC.USE, C.NAME, C.PRICE\n"
						+ "FROM CUSTOMER_COUPON CC\n"
						+ "LEFT OUTER JOIN COUPON C ON C.SEQ = CC.COUPON_SEQ\n"
						+ "WHERE CC.USERS_SEQ = ? AND CC.USE = 'N'";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Customer_couponDTO> list = new ArrayList<Customer_couponDTO>();
			
			while(rs.next()) {
				
				Customer_couponDTO dto = new Customer_couponDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setUsers_seq(rs.getString("users_seq"));
				dto.setCoupon_seq(rs.getString("coupon_seq"));
				dto.setUse(rs.getString("use"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("Customer_couponDAO.getOrderCouponList()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
