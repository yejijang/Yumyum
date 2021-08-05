package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.yumyum.CheckLocation;
import com.yumyum.DBUtil;
import com.yumyum.dto.Cs_questionDTO;
import com.yumyum.dto.ShopDTO;

public class Cs_questionDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public Cs_questionDAO() {

		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("Cs_questionDAO.Cs_questionDAO()");
			e.printStackTrace();
		}
	}

	public ArrayList<Cs_questionDTO> getQuestion(String seq) {
		
		try {

			String sql = "select \r\n"
					+ "  2000 as searchflag,\r\n"
					+ "  csq.content as content,\r\n"
					+ "  csq.title as title,\r\n"
					+ "  css.cs_division as cs_division\r\n"
					+ "  from CS_QUESTION csq\r\n"
					+ "  inner join cs_subject css\r\n"
					+ "  on csq.cs_subject_seq=css.seq\r\n"
					+ "  where css.seq= ? ";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			
			ArrayList<Cs_questionDTO> cslist = new ArrayList<Cs_questionDTO>();

			while(rs.next()) {

				Cs_questionDTO csdto = new Cs_questionDTO();

				csdto.setTitle(rs.getString("title"));
				csdto.setSearchflag(rs.getString("searchflag"));
				csdto.setContent(rs.getString("content"));
		
				cslist.add(csdto);
			}
			
			return cslist;

		} catch (Exception e) {
			System.out.println("ShopDTO.get()");
			e.printStackTrace();
		}


		
		return null;
	}

	public ArrayList<Cs_questionDTO> getSearchQuestion(HashMap<String, String> map) {
		try {

			String where = "";

			// 검색일 경우
			if (map.get("isSearch").equals("y")) {
				where = String.format(" AND CONTENT LIKE '%%%s%%' ", map.get("searchWord"));
			}

			String sql = String.format("  select \r\n" + " 1000 as searchflag,\r\n"+ "  csq.content as content,\r\n" + "  csq.title as title,\r\n"
					+ "  css.cs_division as cs_division\r\n" + "  from CS_QUESTION csq\r\n"
					+ "  inner join cs_subject css\r\n" + "  on csq.cs_subject_seq=css.seq\r\n"
					+ "  where css.seq %s %s ", map.get("SearchRange"), where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<Cs_questionDTO> list = new ArrayList<Cs_questionDTO>(); // 옮겨 담을 큰 상자

			while (rs.next()) {

				Cs_questionDTO dto = new Cs_questionDTO();

				dto.setSearchflag(rs.getString("searchflag"));
				dto.setContent(rs.getString("content"));
				dto.setTitle(rs.getString("title"));
				
				list.add(dto);
			}

			return list;
			
		} catch (Exception e) {
			System.out.println("Cs_questionDTO.getSearchQuestion()");
			e.printStackTrace();
		}

		return null;
	}

}
























