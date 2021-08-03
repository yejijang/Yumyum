package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Review_noticeDTO;

public class Review_noticeDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Review_noticeDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Review_noticeDAO.Review_noticeDAO()");
			e.printStackTrace();
		}
	}

	public Review_noticeDTO getRevnotice(String seq) {
		try {
			
			String sql = "select "
					+ "content,"
					+ "TO_CHAR(rvn.start_date, 'YYYY-MM-DD') as start_date,"
					+ "TO_CHAR(end_date, 'YYYY-MM-DD') as end_date from shop s\r\n"
					+ "inner join REVIEW_NOTICE rvn on s.seq=rvn.shop_seq and s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				Review_noticeDTO rvndto = new Review_noticeDTO();
				
				rvndto.setContent(rs.getString("content"));
				rvndto.setStart_date(rs.getString("start_date"));
				rvndto.setEnd_date(rs.getString("end_date"));
				
				return rvndto;
			}

		} catch (Exception e) {
			System.out.println("getRevnotice()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
