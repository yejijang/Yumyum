package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Order_menuDTO;
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

	//shop.do에서 사용자번호로 리뷰작성자의 주문메뉴를 찾아주세요
	public ArrayList<Order_menuDTO> getOrderlistDAO(String seq) {
		
		
		try {
			
			String sql = "select \r\n"
					+ "r.seq as review_seq, \r\n"
					+ "m.name as menu_name, \r\n"
					+ "mo.name as option_name\r\n"
					+ "from review r\r\n"
					+ "left outer join orderlist o on r.orderlist_seq = o.seq\r\n"
					+ "left outer join order_menu om on om.orderlist_seq = o.seq\r\n"
					+ "left outer join menu m on m.seq = om.menu_seq\r\n"
					+ "left outer join menu_option mo on mo.seq = om.menu_option_seq\r\n"
					+ "where r.shop_seq = ?\r\n"
					+ "order by r.seq";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Order_menuDTO> omlist = new ArrayList<Order_menuDTO>();
			
			while (rs.next()) {
				
				Order_menuDTO dto = new Order_menuDTO();
				
				dto.setReview_seq(rs.getString("review_seq"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setOption_name(rs.getString("option_name"));
				
				omlist.add(dto);
			}
			
			return omlist;
			
		} catch (Exception e) {
			System.out.println("OrderlistDAO.getOrderlistDAO()");
			e.printStackTrace();
		}
		
		return null;
	}

}
