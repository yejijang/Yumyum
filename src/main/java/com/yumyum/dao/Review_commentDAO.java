package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Menu_groupDTO;
import com.yumyum.dto.Review_commentDTO;

public class Review_commentDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Review_commentDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Review_commentDAO.Review_commentDAO()");
			e.printStackTrace();
		}
	}

	public ArrayList<Review_commentDTO> getReviewComment(String seq) {
		
		try {
			
			String sql = "select \r\n"
					+ "rc.review_seq as review_seq,\r\n"
					+ "rc.regdate as regdate,\r\n"
					+ "rc.content as content\r\n"
					+ "from shop s\r\n"
					+ "inner join REVIEW r\r\n"
					+ "on r.shop_seq = s.seq\r\n"
					+ "inner join Review_Comment rc\r\n"
					+ "on rc.review_seq = r.seq \r\n"
					+ "where s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Review_commentDTO> rvclist = new ArrayList<Review_commentDTO>();
			
			while(rs.next()) {
				
				Review_commentDTO rvcdto = new Review_commentDTO();
				
				rvcdto.setReview_seq(rs.getString("review_seq"));
				rvcdto.setRegdate(rs.getString("regdate"));
				rvcdto.setContent(rs.getString("content"));
				
				rvclist.add(rvcdto);
			}
			
			return rvclist;
			
		} catch (Exception e) {
			System.out.println("Review_commentDAO.getReviewComment()");
			e.printStackTrace();
		}
		return null;
	}
}
