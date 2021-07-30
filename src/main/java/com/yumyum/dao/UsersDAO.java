package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.yumyum.DBUtil;
import com.yumyum.dto.UsersDTO;

public class UsersDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public UsersDAO() {

		try {
			conn = DBUtil.open();
		} catch (Exception e) {
			System.out.println("UsersDAO.UsersDAO()");
			e.printStackTrace();
		}

	}

	public UsersDTO login(UsersDTO dto) {

		try {

			String sql = "select * from Users where id=? and password=?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPassword());

			rs = pstat.executeQuery();

			if (rs.next()) {
				UsersDTO result = new UsersDTO();

				result.setId(rs.getString("id"));
				result.setAuth(rs.getString("auth"));
				result.setSeq(rs.getString("seq"));
				result.setName(rs.getString("name"));

				return result;
			}
			// 없으면 null 반환

		} catch (Exception e) {
			System.out.println("UsersDAO.login()");
			e.printStackTrace();
		}

		return null;
	}

}