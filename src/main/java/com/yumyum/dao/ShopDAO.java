package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.yumyum.CheckLocation;
import com.yumyum.DBUtil;
import com.yumyum.dto.CategoryDTO;
import com.yumyum.dto.ShopDTO;
import com.yumyum.dto.UsersDTO;

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
	//Shoplist에서 받은 값들로 가게를 조회한다.
	public ArrayList<ShopDTO> getListshop(HashMap<String, String> map) {

		try {

			String where = "";
			
			//검색일 경우
			if(map.get("isSearch").equals("y")) {
				where = String.format(" AND NAME LIKE '%%%s%%' ", map.get("searchWord"));
			}
			
			String sql = String.format("SELECT * FROM VW_SHOP_LIST WHERE CATEGORY_SEQ = %s %s ", map.get("seq"), where);
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<ShopDTO> list = new ArrayList<ShopDTO>(); //옮겨 담을 큰 상자
			CheckLocation cl = new CheckLocation();
			
			while(rs.next()) {
				
				//DB에 저장된 가게들의 주소로 좌표(위도, 경도)를 받아 온다.
				ShopDTO location = cl.getLocation(rs.getString("address"));
				
				if(location != null) {
					
					//소비자의 좌표와 DB에 저장된 가게들의 좌표를 통해 둘 사이의 거리를 계산하여 KM로 리턴 받는다. 
					double distance = cl.getDistance(map.get("lon"), map.get("lat"), location.getLon(), location.getLat());
					
					ShopDTO dto = new ShopDTO();
				
					dto.setSeq(rs.getString("seq"));
					dto.setName(rs.getString("name"));
					dto.setExplanation(rs.getString("explanation"));
					dto.setPicture(rs.getString("picture"));
					dto.setMin_price(rs.getString("min_price"));
					dto.setAvg_score(rs.getString("avg_score"));
					dto.setEvent_flag(rs.getString("event_flag"));
					
					//5km 이내에 있는 가게들만 리스트에 담는다.
					if(distance <= 5) {
						list.add(dto);
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

	//Shop_add(가게 입점 신청)에서 카테고리를 select 박스로 보여 준다.
	public ArrayList<CategoryDTO> getCategoryList() {

		try {
			
			String sql = "SELECT * FROM CATEGORY ORDER BY SEQ";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();
			
			while(rs.next()) {
			
				CategoryDTO dto = new CategoryDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setIcon(rs.getString("icon"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ShopDAO.getCategoryList()");
			e.printStackTrace();
		}

		return null;
	}

	public int addShop(ShopDTO dto) {

		try {
			
			String sql = "insert into shop(seq, users_seq, category_seq, name, phone, address, registration, file1, file2, regdate) "
					+ "values (seq_shop.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getUsers_seq());
			pstat.setString(2, dto.getCategory_seq());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getRegistration());
			pstat.setString(7, dto.getFile1());
			pstat.setString(8, dto.getFile2());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("ShopDAO.addShop()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ShopDTO getAuth(String users_seq) {

		try {
			
			String sql = "select * from shop where users_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, users_seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				ShopDTO dto = new ShopDTO();
				
				dto.setAuth(rs.getString("auth"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("ShopDAO.getAuth()");
			e.printStackTrace();
		}

		return null;
		
	}
	
	public int getShopTip(String seq) {
		try {

			String sql = "SELECT TIP FROM SHOP WHERE SEQ = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				int result = Integer.parseInt(rs.getString("tip"));
				return result;
			}

		} catch (Exception e) {
			System.out.println("ShopDTO.getShopTip()");
			e.printStackTrace();
		}

		return 0;
	}
	
}