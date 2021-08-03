package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.yumyum.DBUtil;
import com.yumyum.dto.ShopDTO;

public class ShopDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ShopDAO() {

		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("ShopDAO.ShopDAO()");
			e.printStackTrace();
		}
	}

	public ShopDTO getShop(String seq) {
		try {

			String sql = "select \r\n"
					+ "s.name as name,\r\n"
					+ "s.seq as seq,\r\n"
					+ "s.users_seq as users_seq,\r\n"
					+ "s.phone as phone,\r\n"
					+ "s.address as address,\r\n"
					+ "s.registration as registration,\r\n"
					+ "s.regdate as regdate,\r\n"
					+ "FN_FORMAT_WON(s.min_price) as min_price,\r\n"
					+ "FN_FORMAT_WON(s.tip) as tip,\r\n"
					+ "s.explanation as explanation,\r\n"
					+ "s.picture as picture,\r\n"
					+ "c.name as category\r\n"
					+ "from shop s\r\n"
					+ "inner join category c\r\n"
					+ "on s.category_seq = c.seq\r\n"
					+ "where s.seq=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				ShopDTO shopdto = new ShopDTO();

				shopdto.setName(rs.getString("name"));
				shopdto.setCategory(rs.getString("category"));
				shopdto.setSeq(rs.getString("seq"));
				shopdto.setUsers_seq(rs.getString("users_seq"));
				shopdto.setPhone(rs.getString("phone"));
				shopdto.setAddress(rs.getString("address"));
				shopdto.setRegistration(rs.getString("registration"));
				shopdto.setMin_price(rs.getString("min_price"));
				shopdto.setRegdate(rs.getString("regdate"));
				shopdto.setExplanation(rs.getString("explanation"));
				shopdto.setPicture(rs.getString("picture"));
				shopdto.setTip(rs.getString("tip"));

				return shopdto;
			}

		} catch (Exception e) {
			System.out.println("ShopDTO.get()");
			e.printStackTrace();
		}

		return null;
	}
}