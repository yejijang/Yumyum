package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Customer_couponDTO;
import com.yumyum.dto.Review_menuDTO;

public class Review_menuDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Review_menuDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Review_menuDAO.Review_menuDAO()");
			e.printStackTrace();
		}

	}

	public ArrayList<Review_menuDTO> getReviewMenu(String seq) {
		
		try {
			
			String sql = "    select rvm.name as name from SHOP s \r\n"
					+ "    inner join REVIEW_NOTICE rvn on s.seq=rvn.SHOP_SEQ\r\n"
					+ "    inner join REVIEW_MENU rvm on rvn.seq=rvm.REVIEW_NOTICE_SEQ\r\n"
					+ "    where s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Review_menuDTO> rvmlist = new ArrayList<Review_menuDTO>();
			
			while(rs.next()) {
				
				Review_menuDTO rvmdto = new Review_menuDTO();
				
				rvmdto.setName(rs.getString("name"));
				
				rvmlist.add(rvmdto);
			}
			
			return rvmlist;
			
		} catch (Exception e) {
			System.out.println("Review_menuDAO.getReviewMenu()");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
