package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.yumyum.DBUtil;
import com.yumyum.dto.Plus_priceDTO;
import com.yumyum.dto.ShopDTO;

public class Plus_priceDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public Plus_priceDAO() {
		
		try {
			
			conn = DBUtil.open();
			
		} catch (Exception e) {
			System.out.println("Plus_priceDAO.Plus_priceDAO()");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Plus_priceDTO> getPprice(String seq) {


		try {

			String sql = "select  FN_FORMAT_WON(p.price)as price, p.dong as dong from shop s\r\n"
					+ "inner join PLUS_PRICE p on s.seq=p.shop_seq and s.seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<Plus_priceDTO> pplist = new ArrayList<Plus_priceDTO>();
			
			while(rs.next()) {
				
				Plus_priceDTO ppdto = new Plus_priceDTO();
				
				ppdto.setPrice(rs.getString("price"));
				ppdto.setDong(rs.getString("dong"));

				pplist.add(ppdto);
			}
			
			return pplist;

		} catch (Exception e) {
			System.out.println("Plus_priceDTO.getPprice()");
			e.printStackTrace();
		}
		
		return null;
	}

}
