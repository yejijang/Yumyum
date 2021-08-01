package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.OrderlistDTO;

public class OrderlistDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public OrderlistDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("OrderlistDAO.OrderlistDAO()");
			e.printStackTrace();
		}
	}

	public ArrayList<OrderlistDTO> getListCategoryCnt(String seq) {
		
		try {
			
			String sql = "SELECT C.NAME, COUNT(*) AS CNT\r\n"
					+ "FROM ORDERLIST O\r\n"
					+ "INNER JOIN SHOP S\r\n"
					+ "ON O.SHOP_SEQ = S.SEQ\r\n"
					+ "INNER JOIN CATEGORY C\r\n"
					+ "ON S.CATEGORY_SEQ = C.SEQ\r\n"
					+ "WHERE O.USERS_SEQ = ?\r\n"
					+ "GROUP BY C.NAME";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<OrderlistDTO> list = new ArrayList<OrderlistDTO>();
			
			while (rs.next()) {
				OrderlistDTO dto = new OrderlistDTO();
				dto.setName(rs.getString("name"));
				dto.setCnt(rs.getString("cnt"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OrderlistDAO.getListCategoryCnt()");
			e.printStackTrace();
		}

		return null;
	}

}
