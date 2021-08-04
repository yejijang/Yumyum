package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.CheckLocation;
import com.yumyum.DBUtil;
import com.yumyum.dto.ShopDTO;

public class ShopDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ShopDAO() {

		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("ShopDAO.ShopDAO()");
			e.printStackTrace();
		}
	}

	public ShopDTO getShop(String seq) {
		try {

			String sql = "select \r\n"
					+ "s.name as name,\r\n"
					+ "s.seq as seq,\r\n"
					+ "s.users_seq as users_seq,\r\n"
					+ "s.phone as phone,\r\n"
					+ "s.address as address,\r\n"
					+ "s.registration as registration,\r\n"
					+ "s.regdate as regdate,\r\n"
					+ "FN_FORMAT_WON(s.min_price) as min_price,\r\n"
					+ "FN_FORMAT_WON(s.tip) as tip,\r\n"
					+ "s.explanation as explanation,\r\n"
					+ "s.picture as picture,\r\n"
					+ "c.name as category\r\n"
					+ "from shop s\r\n"
					+ "inner join category c\r\n"
					+ "on s.category_seq = c.seq\r\n"
					+ "where s.seq=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				ShopDTO shopdto = new ShopDTO();

				shopdto.setName(rs.getString("name"));
				shopdto.setCategory(rs.getString("category"));
				shopdto.setSeq(rs.getString("seq"));
				shopdto.setUsers_seq(rs.getString("users_seq"));
				shopdto.setPhone(rs.getString("phone"));
				shopdto.setAddress(rs.getString("address"));
				shopdto.setRegistration(rs.getString("registration"));
				shopdto.setMin_price(rs.getString("min_price"));
				shopdto.setRegdate(rs.getString("regdate"));
				shopdto.setExplanation(rs.getString("explanation"));
				shopdto.setPicture(rs.getString("picture"));
				shopdto.setTip(rs.getString("tip"));

				return shopdto;
			}

		} catch (Exception e) {
			System.out.println("ShopDTO.get()");
			e.printStackTrace();
		}

		return null;
	}
	
	
	//사진, 가게명, 가게설명, 평점, 최소주문금액, 이벤트
	//Category(카테고리)에서 seq를 넘겨 받아 사용 가능 카테고리를 조회한다.
	public ArrayList<ShopDTO> getListshop(String seq, String lon, String lat) {

		try {
			
			String sql = "SELECT S.SEQ, S.NAME, S.EXPLANATION, S.PICTURE, FN_FORMAT_WON(S.MIN_PRICE) AS MIN_PRICE,"
					+ " NVL(R.AVG_SCORE, 0) AS AVG_SCORE, NVL2(RN.SEQ, 'Y', 'N') AS EVENT_FLAG, S.ADDRESS\r\n"
					+ "FROM SHOP S\r\n"
					+ "LEFT OUTER JOIN \r\n"
					+ "    (SELECT SHOP_SEQ, ROUND(AVG(SCORE), 1) AVG_SCORE\r\n"
					+ "    FROM REVIEW R\r\n"
					+ "    GROUP BY SHOP_SEQ) R \r\n"
					+ "ON S.SEQ = R.SHOP_SEQ\r\n"
					+ "LEFT OUTER JOIN REVIEW_NOTICE RN\r\n"
					+ "ON S.SEQ = RN.SHOP_SEQ\r\n"
					+ "AND RN.END_DATE >= SYSDATE\r\n"
					+ "WHERE S.CATEGORY_SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<ShopDTO> list = new ArrayList<ShopDTO>();
			CheckLocation cl = new CheckLocation();
			
			while(rs.next()) {
				
				ShopDTO location = cl.getLocation(rs.getString("address"));
				
				if(location != null) {
					
					double distance = cl.getDistance(lon, lat, location.getLon(), location.getLat());
					
					ShopDTO dto = new ShopDTO();
					
					dto.setSeq(rs.getString("seq"));
					dto.setName(rs.getString("name"));
					dto.setExplanation(rs.getString("explanation"));
					dto.setPicture(rs.getString("picture"));
					dto.setMin_price(rs.getString("min_price"));
					dto.setAvg_score(rs.getString("avg_score"));
					dto.setEvent_flag(rs.getString("event_flag"));
					
					//5km이내에 있는 가게들 출력
					if(distance <= 5) {
						list.add(dto);
					} else {
						System.out.println(rs.getString("name"));
						System.out.println(distance);
					}
				}
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ShopDAO.getListshop()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}