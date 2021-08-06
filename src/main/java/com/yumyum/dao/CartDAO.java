package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.CartDTO;
import com.yumyum.dto.MenuDTO;
import com.yumyum.dto.ShopDTO;

public class CartDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CartDAO() {

		try {
			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("CartDAO.CartDAO()");
			e.printStackTrace();
		}
	}

	public int addCart(CartDTO dto) {

		try {

			String sql = "INSERT INTO CART(SEQ, USERS_SEQ, MENU_SEQ, MENU_OPTION_SEQ, CNT)\r\n"
					+ "VALUES (SEQ_CART.NEXTVAL, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getUsers_seq());
			pstat.setString(2, dto.getMenu_seq());
			pstat.setString(3, dto.getMenu_option_seq());
			pstat.setInt(4, dto.getCnt());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CartDAO.addCart()");
			e.printStackTrace();
		}

		return 0;
	}

	public ArrayList<ShopDTO> cartShopList(String seq) {

		try {

			String sql = "SELECT DISTINCT S.SEQ, S.NAME\r\n" + "FROM CART C\r\n"
					+ "LEFT OUTER JOIN MENU M ON M.SEQ = C.MENU_SEQ\r\n"
					+ "LEFT OUTER JOIN MENU_GROUP MG ON MG.SEQ = M.MENU_GROUP_SEQ\r\n"
					+ "LEFT OUTER JOIN SHOP S ON S.SEQ = MG.SHOP_SEQ\r\n" + "WHERE C.USERS_SEQ = ?\r\n"
					+ "ORDER BY S.SEQ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<ShopDTO> list = new ArrayList<ShopDTO>();

			while (rs.next()) {

				ShopDTO dto = new ShopDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("CartDAO.cartList()");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<CartDTO> cartMenuList(String seq) {

		try {

			String sql = "SELECT C.SEQ, C.MENU_SEQ, M.NAME AS MENU_NAME, M.PRICE AS PRICE, C.CNT, S.SEQ AS SHOP_SEQ, C.MENU_OPTION_SEQ, MO.NAME AS MENU_OPTION_NAME, MO.PRICE AS MENU_OPTION_PRICE\r\n"
					+ "FROM CART C\r\n" + "LEFT OUTER JOIN MENU M ON M.SEQ = C.MENU_SEQ\r\n"
					+ "LEFT OUTER JOIN MENU_GROUP MG ON MG.SEQ = M.MENU_GROUP_SEQ\r\n"
					+ "LEFT OUTER JOIN SHOP S ON S.SEQ = MG.SHOP_SEQ\r\n"
					+ "LEFT OUTER JOIN MENU_OPTION MO ON MO.SEQ = C.MENU_OPTION_SEQ\r\n" + "WHERE C.USERS_SEQ = ?\r\n"
					+ "ORDER BY S.SEQ, C.MENU_SEQ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<CartDTO> list = new ArrayList<CartDTO>();

			while (rs.next()) {

				CartDTO dto = new CartDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setMenu_seq(rs.getString("menu_seq"));
				dto.setMenu_name(rs.getString("menu_name"));
				dto.setMenu_price(rs.getInt("price"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setShop_seq(rs.getString("shop_seq"));
				dto.setMenu_option_seq(rs.getString("menu_option_seq"));
				dto.setMenu_option_name(rs.getString("menu_option_name"));
				dto.setMenu_option_price(rs.getString("menu_option_price"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("CartDAO.cartList()");
			e.printStackTrace();
		}
		return null;
	}

	public int delCart(String seq) {

		try {

			String sql = "DELETE FROM CART WHERE SEQ = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CartDAO.delCart()");
			e.printStackTrace();
		}

		return 0;
	}
}