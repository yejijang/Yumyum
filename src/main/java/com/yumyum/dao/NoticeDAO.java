package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.yumyum.DBUtil;
import com.yumyum.dto.Cs_questionDTO;
import com.yumyum.dto.NoticeDTO;

public class NoticeDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NoticeDAO() {

		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("NoticeDAO.NoticeDAO()");
			e.printStackTrace();
		}
	}

	public ArrayList<NoticeDTO> getNoticeList(HashMap<String, String> map) {
		
		try {

			String where = "";

			// 검색일 경우
			if (map.get("isSearch").equals("y")) {
				where = String.format(" AND SUBJECT LIKE '%%%s%%' OR CONTENT LIKE '%%%s%%' ", map.get("searchWord"), map.get("searchWord"));
			}

			/*
			 * String sql = String.format("SELECT * FROM (\r\n" +
			 * "    SELECT ROWNUM AS RNUM, N.*\r\n" +
			 * "    FROM (SELECT SEQ, SUBJECT, CONTENT, TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE\r\n"
			 * + "        FROM NOTICE ORDER BY REGDATE DESC\r\n" + "        ) N\r\n" +
			 * ") WHERE RNUM BETWEEN %s AND %s %s ORDER BY REGDATE DESC", map.get("begin"),
			 * map.get("end"), where);
			 */
			String sql = String.format("SELECT *\r\n"
					+ "FROM (\r\n"
					+ "    SELECT a.*, ROWNUM as RNUM\r\n"
					+ "    FROM (\r\n"
					+ "        SELECT N.SEQ, SUBJECT, CONTENT, TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE\r\n"
					+ "        FROM NOTICE N\r\n"
					+ "        WHERE 1=1 %s\r\n"
					+ "        ORDER BY REGDATE DESC\r\n"
					+ "    ) a\r\n"
					+ ") WHERE RNUM BETWEEN %s AND %s", where, map.get("begin"), map.get("end"));
System.out.println(sql);
			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>(); // 옮겨 담을 큰 상자

			while (rs.next()) {

				NoticeDTO dto = new NoticeDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}

			return list;
			
		} catch (Exception e) {
			System.out.println("NoticeDAO.getNoticeList()");
			e.printStackTrace();
		}

		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String where = "";
			
			if(map.get("isSearch").equals("y")) {
				
				if (map.get("isSearch").equals("y")) {
					where = String.format(" WHERE SUBJECT LIKE '%%%s%%' OR CONTENT LIKE '%%%s%%' ", map.get("searchWord"), map.get("searchWord"));
				}
			}
			
			String sql = String.format("select count(*) as cnt from NOTICE %s", where);
			//System.out.println(sql);
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("NOTICEDAO.getTotalCount()");
			e.printStackTrace();
		}
		
		return 0;
	}
}
