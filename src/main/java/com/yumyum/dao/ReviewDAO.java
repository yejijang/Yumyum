package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.ReviewDTO;

public class ReviewDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ReviewDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("ReviewDAO.ReviewDAO()");
			e.printStackTrace();
		}
		
	}

	public ArrayList<ReviewDTO> getReview(String seq) {
		
		try {

			String sql = "select  \r\n"
					+ "  R.AVG_SCORE as avg, \r\n"
					+ "  u.seq as user_seq, \r\n"
					+ "  rv.seq as seq, \r\n"
					+ "  rv.score as score, \r\n"
					+ "  u.nickname as nickname, \r\n"
					+ "  rv.content as content,  \r\n"
					+ "  rv.picture as picture,  \r\n"
					+ "  TO_DATE(rv.regdate,'YYYY-MM-DD') as regdate,  \r\n"
					+ "  rv.recommend as recommend, \r\n"
					+ "  rv.nonrecommend as nonrecommend,  \r\n"
					+ "  o.seq as orderlist_seq  \r\n"
					+ "  from USERS u   \r\n"
					+ "  inner join ORDERLIST o  \r\n"
					+ "  on u.seq = o.users_seq  \r\n"
					+ "  inner join REVIEW rv  \r\n"
					+ "  on rv.orderlist_seq =  o.seq \r\n"
					+ "  inner join SHOP s  \r\n"
					+ "  on rv.shop_seq=s.seq \r\n"
					+ "  left outer join  \r\n"
					+ "  (SELECT SHOP_SEQ, AVG(SCORE) AVG_SCORE \r\n"
					+ "  FROM REVIEW R \r\n"
					+ "  GROUP BY SHOP_SEQ) R  \r\n"
					+ "  ON S.SEQ = R.SHOP_SEQ \r\n"
					+ "  where s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ReviewDTO> rvlist = new ArrayList<ReviewDTO>();
			
			while(rs.next()) {
				
				ReviewDTO rvdto = new ReviewDTO();
				
				rvdto.setAvg(rs.getString("avg"));
				rvdto.setSeq(rs.getString("seq"));
				rvdto.setUser_seq(rs.getString("user_seq"));
				rvdto.setNickname(rs.getString("nickname"));
				rvdto.setContent(rs.getString("content"));
				rvdto.setScore(rs.getString("score"));
				rvdto.setPicture(rs.getString("picture"));
				rvdto.setRegdate(rs.getString("regdate"));
				rvdto.setRecommend(rs.getString("recommend"));
				rvdto.setNonrecommend(rs.getString("nonrecommend"));
				rvdto.setOrderlist_seq(rs.getString("orderlist_seq"));
				
				rvlist.add(rvdto);
			}
			
			return rvlist;
			
		}catch (Exception e) {
			System.out.println("ReviewDTO.getReview()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}
