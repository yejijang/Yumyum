package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Menu_groupDTO;
import com.yumyum.dto.Review_menuDTO;

public class Menu_groupDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Menu_groupDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Menu_groupDAO.Menu_groupDAO()");
			e.printStackTrace();
		}
	}

	public ArrayList<Menu_groupDTO> getMenuGroup(String seq) {

		try {
			
			String sql = "select seq, name from MENU_GROUP where shop_seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Menu_groupDTO> mglist = new ArrayList<Menu_groupDTO>();
			
			while(rs.next()) {
				
				Menu_groupDTO mgdto = new Menu_groupDTO();
				
				mgdto.setSeq(rs.getString("seq"));
				mgdto.setName(rs.getString("name"));
				
				mglist.add(mgdto);
			}
			
			return mglist;
			
		} catch (Exception e) {
			System.out.println("Review_menuDAO.getReviewMenu()");
			e.printStackTrace();
		}
		return null;
	}
}
