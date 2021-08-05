package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.MenuDTO;
import com.yumyum.dto.Menu_optionDTO;
import com.yumyum.dto.OrderlistDTO;

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

			String sql = "select \n"
						+ "m.menu_group_seq as menu_group_seq,\n"
						+ "m.seq as menu_seq,\n"
						+ "m.name as name,\n"
						+ "FN_FORMAT_WON(m.price) as price,\n"
						+ "m.picture as picture,\n"
						+ "m.explanation as explanation\n"
						+ "from SHOP s\n"
						+ "inner join MENU_GROUP mg\n"
						+ "on s.seq=mg.shop_seq\n"
						+ "inner join MENU m\n"
						+ "on m.menu_group_seq = mg.seq\n"
						+ "where s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<MenuDTO> mlist = new ArrayList<MenuDTO>();
			
			while(rs.next()) {
				
				MenuDTO mdto = new MenuDTO();

				mdto.setMenu_group_seq(rs.getString("menu_group_seq"));
				mdto.setSeq(rs.getString("menu_seq"));
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

	public ArrayList<Menu_optionDTO> getMenuOption(String seq) {
		
		try {
			
			String sql = "SELECT MO.SEQ, MO.MENU_SEQ, MO.NAME, FN_FORMAT_WON(MO.PRICE) AS PRICE\n"
						+ "FROM MENU_OPTION MO\n"
						+ "WHERE MO.MENU_SEQ = ?\n"
						+ "ORDER BY MO.SEQ ASC";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Menu_optionDTO> list = new ArrayList<Menu_optionDTO>();
			
			while (rs.next()) {
				Menu_optionDTO dto = new Menu_optionDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setMenu_seq(rs.getString("menu_seq"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("MenuDAO.getMenuOption()");
			e.printStackTrace();
		}
		
		return null;
	}


}
