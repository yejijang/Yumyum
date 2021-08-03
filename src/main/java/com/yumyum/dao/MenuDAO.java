package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.MenuDTO;

public class MenuDAO {
	

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MenuDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("MenuDAO.MenuDAO()");
			e.printStackTrace();
		}
		
	}

	public ArrayList<MenuDTO> getMenu(String seq) {
		
		try {

			String sql = "select \r\n"
					+ "m.menu_group_seq as menu_group_seq,\r\n"
					+ "m.name as name,\r\n"
					+ "FN_FORMAT_WON(m.price) as price,\r\n"
					+ "m.picture as picture,\r\n"
					+ "m.explanation as explanation\r\n"
					+ "from SHOP s\r\n"
					+ "inner join MENU_GROUP mg\r\n"
					+ "on s.seq=mg.shop_seq\r\n"
					+ "inner join MENU m\r\n"
					+ "on m.menu_group_seq = mg.seq\r\n"
					+ "where s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MenuDTO> mlist = new ArrayList<MenuDTO>();
			
			while(rs.next()) {
				
				MenuDTO mdto = new MenuDTO();

				mdto.setMenu_group_seq(rs.getString("menu_group_seq"));
				mdto.setName(rs.getString("name"));
				mdto.setPrice(rs.getString("price"));
				mdto.setPicture(rs.getString("picture"));
				mdto.setExplanation(rs.getString("explanation"));

				
				mlist.add(mdto);
			}
			
			return mlist;
			
		}catch (Exception e) {
			System.out.println("MenuDAO.getMenu()");
			e.printStackTrace();
		}
		return null;
	}


}
