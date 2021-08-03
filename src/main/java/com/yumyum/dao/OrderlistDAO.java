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

	public ArrayList<OrderlistDTO> getOrderlist(String seq, String begin, String end) {

		try {

			String sql = String.format("SELECT *\n"
									+ "FROM (\n"
									+ "    SELECT O.SEQ, O.SHOP_SEQ, S.NAME, S.PICTURE, O.REGDATE, O.STATUS, FN_FORMAT_WON(O.CHARGE_PRICE) AS CHARGE_PRICE, ROW_NUMBER() OVER(ORDER BY O.REGDATE DESC) AS RNUM\n"
									+ "    FROM ORDERLIST O\n"
									+ "    LEFT OUTER JOIN SHOP S ON O.SHOP_SEQ = S.SEQ\n"
									+ "    WHERE O.USERS_SEQ = ?\n"
									+ "    ORDER BY O.REGDATE DESC\n"
									+ ")\n"
									+ "WHERE RNUM BETWEEN %s AND %s", begin, end);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<OrderlistDTO> list = new ArrayList<OrderlistDTO>();

			while (rs.next()) {
				OrderlistDTO dto = new OrderlistDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setShop_seq(rs.getString("shop_seq"));
				dto.setShop_name(rs.getString("name"));
				dto.setShop_picture(rs.getString("picture"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setStatus(rs.getString("status"));
				dto.setCharge_price(rs.getString("charge_price"));
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("OrderlistDAO.getOrderlist()");
			e.printStackTrace();
		}

		return null;
	}
	
	public int getTotalOrderlist(String seq) {
		
		try {
			
			String sql = "SELECT COUNT(*) AS CNT\n"
						+ "FROM ORDERLIST O\n"
						+ "LEFT OUTER JOIN SHOP S ON O.SHOP_SEQ = S.SEQ\n"
						+ "WHERE O.USERS_SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("OrderlistDAO.getTotalOrderlist()");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public ArrayList<Order_menuDTO> getOrdermenu(String seq) {

		try {

			String sql = "SELECT O.ORDERLIST_SEQ, O.MENU_SEQ, M.NAME AS MENU_NAME, M.PICTURE, O.CNT, MO.NAME AS OPTION_NAME\n"
						+ "FROM ORDER_MENU O LEFT OUTER JOIN MENU M ON O.MENU_SEQ = M.SEQ\n"
						+ "LEFT JOIN ORDERLIST OL ON OL.SEQ = O.ORDERLIST_SEQ\n"
						+ "LEFT JOIN MENU_OPTION MO ON MO.SEQ = O.MENU_OPTION_SEQ\n"
						+ "WHERE OL.USERS_SEQ = ?\n"
						+ "ORDER BY O.ORDERLIST_SEQ, O.MENU_SEQ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<Order_menuDTO> list = new ArrayList<Order_menuDTO>();

			while (rs.next()) {
				Order_menuDTO dto = new Order_menuDTO();
				dto.setOrderlist_seq(rs.getString("orderlist_seq"));
				dto.setMenu_seq(rs.getString("menu_seq"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_picture(rs.getString("picture"));
				dto.setCnt(rs.getString("cnt"));
				dto.setOption_name(rs.getString("option_name"));
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("OrderlistDAO.getOrdermenu()");
			e.printStackTrace();
		}

		return null;
	}

	public int delOrderlist(String seq) {
		
		try {
			
			String sql = "DELETE FROM ORDERLIST WHERE SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("OrderlistDAO.delOrderlist()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<OrderlistDTO> getOrderInfo(String seq) {

		try {

			String sql = "SELECT O.SEQ, S.NAME, O.STATUS, O.CANCEL_REASON_SEQ, CR.CONTENT, O.REGDATE, O.ORDER_NUM, O.DELIVERY_ADDRESS, U.PHONE, O.REQUEST_OWNER, O.REQUEST_RIDER, FN_FORMAT_WON(O.ORDER_PRICE) AS ORDER_PRICE, FN_FORMAT_WON(S.TIP) AS TIP, FN_FORMAT_WON(O.CHARGE_PRICE) AS CHARGE_PRICE, O.CHARGE_METHOD\n"
						+ "FROM ORDERLIST O LEFT OUTER JOIN SHOP S ON S.SEQ = O.SHOP_SEQ\n"
						+ "LEFT OUTER JOIN USERS U ON U.SEQ = O.USERS_SEQ\n"
						+ "LEFT OUTER JOIN CANCEL_REASON CR ON CR.SEQ = O.CANCEL_REASON_SEQ\n"
						+ "WHERE O.SEQ = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<OrderlistDTO> list = new ArrayList<OrderlistDTO>();

			while (rs.next()) {
				OrderlistDTO dto = new OrderlistDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setStatus(rs.getString("status"));
				dto.setCancel_reason_seq(rs.getString("cancel_reason_seq"));
				dto.setCancel_reason(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setOrder_num(rs.getString("order_num"));
				dto.setDelivery_address(rs.getString("delivery_address"));
				dto.setUser_phone(rs.getString("phone"));
				dto.setRequest_owner(rs.getString("request_owner"));
				dto.setRequest_rider(rs.getString("request_rider"));
				dto.setOrder_price(rs.getString("order_price"));
				dto.setShop_tip(rs.getString("tip"));
				dto.setCharge_price(rs.getString("charge_price"));
				dto.setCharge_method(rs.getString("charge_method"));
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("OrderlistDAO.getOrderInfo()");
			e.printStackTrace();
		}

		return null;
	}

}
