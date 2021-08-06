package com.yumyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	//FindidOk(아이디찾기)에서 이름과 휴대폰번호를 넘겨 받아 찾는 아이디가 있는지 확인
	public UsersDTO findid(UsersDTO dto) {
		
		try {

			String sql = "select id from Users where name=? and replace(phone, '-', '')=? and not id in ('admin')";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPhone());

			rs = pstat.executeQuery();

			if (rs.next()) {
				UsersDTO result = new UsersDTO();

				result.setId(rs.getString("id"));

				return result;
			}

		} catch (Exception e) {
			System.out.println("UsersDAO.findid()");
			e.printStackTrace();
		}
		
		return null;
	}

	//FindpwOk(비밀번호 찾기)에서 아이디, 휴대폰번호, 이메일을 입력 받아 존재하면 seq 리턴
	public UsersDTO findpw(UsersDTO dto) {
		
		try {

			String sql = "select seq from users where id = ? and replace(phone, '-', '') = ? "
					+ "and email = ? and not id in ('admin')";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPhone());
			pstat.setString(3, dto.getEmail());
			
			rs = pstat.executeQuery();

			if (rs.next()) {
				UsersDTO result = new UsersDTO();

				result.setSeq(rs.getString("seq"));

				return result;
			}

		} catch (Exception e) {
			System.out.println("UsersDAO.findpw()");
			e.printStackTrace();
		}		
		
		return null;
	}

	//FindpwOk(비밀번호 찾기)에서 메일로 임시비밀번호를 발송 성공하면 비밀번호를 업데이트 한다.
	public int pwEdit(String seq, String authenticationKey) {

		try {
			
			String sql = "update users set password = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, authenticationKey);
			pstat.setString(2, seq);
			 
			return pstat.executeUpdate(); //성공(1), 실패(0)
			
		} catch (Exception e) {
			System.out.println("UsersDAO.pwEdit()");
			e.printStackTrace();
		}
		
		return 0;
	}

	//Mypage(마이페이지)에서 seq를 넘겨 받아 정보를 조회한다.
	public UsersDTO getMypage(String seq) {

		try {
			
			String sql = "SELECT \r\n"
					+ "    U.NICKNAME, \r\n"
					+ "    FN_CUSTOMER_GRADE(U.SEQ) AS GRADE, \r\n"
					+ "    POINT, \r\n"
					+ "    P.END_DATE, \r\n"
					+ "    P.REMAIN_CNT,\r\n"
					+ "    (SELECT FN_FORMAT_WON(SUM(CHARGE_PRICE)) \r\n"
					+ "    FROM ORDERLIST\r\n"
					+ "    WHERE USERS_SEQ = U.SEQ\r\n"
					+ "    ) AS SUM_PRICE \r\n"
					+ "FROM USERS U\r\n"
					+ "LEFT OUTER JOIN CUSTOMER_DELIVERY_PREMIUM P\r\n"
					+ "ON U.SEQ = P.USERS_SEQ\r\n"
					+ "AND P.END_DATE >= SYSDATE\r\n"
					+ "AND P.REMAIN_CNT > 0\r\n"
					+ "WHERE U.SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				UsersDTO dto = new UsersDTO();
				
				dto.setNickname(rs.getString("nickname"));
				dto.setGrade(rs.getString("grade"));
				dto.setPoint(rs.getString("point"));
				dto.setPremiumEndDate(rs.getString("end_date"));
				dto.setPremiumRemainCnt(rs.getString("remain_cnt"));
				dto.setSumPrice(rs.getString("sum_price"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("UsersDAO.getMypage()");
			e.printStackTrace();
		}

		return null;
	}
	
	// 회원가입 > 아이디중복확인창에서 아이디 중복확인 유무를 조회한다.
	public boolean duplicateIdCheck(String id) {
		
		try {
			String sql = "select id from users where id = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("UsersDAO.duplicateIdCheck()");
			e.printStackTrace();
		}
		return false;
	}

	// 회원가입 > 닉네임중복확인창에서 닉네임 중복확인 유무를 조회한다.
	public boolean duplicateNickCheck(String nick) {
		
		try {
			String sql = "select nickname from users where nickname = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, nick);
			rs = pstat.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("UsersDAO.duplicateNickCheck()");
			e.printStackTrace();
		}
		return false;
	}

	public UsersDTO getMypageEdit(String seq) {

		try {
			String sql = "SELECT NICKNAME, NAME, ID, EMAIL, PASSWORD, PHONE FROM USERS WHERE SEQ = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			rs = pstat.executeQuery();

			if (rs.next()) {

				UsersDTO dto = new UsersDTO();

				dto.setNickname(rs.getString("nickname"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setPhone(rs.getString("phone"));

				return dto;
			}

		} catch (Exception e) {
			System.out.println("UsersDAO.getMypageEdit()");
			e.printStackTrace();
		}
		
		return null;
		
	}

	public int updateUser(UsersDTO dto) {
		
		try {
			String sql = "UPDATE USERS SET NICKNAME = ?, PASSWORD = ?, PHONE = ?, EMAIL = ? WHERE SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getNickname());
			pstat.setString(2, dto.getPassword());
			pstat.setString(3, dto.getPhone());
			pstat.setString(4, dto.getEmail());
			pstat.setString(5, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("UsersDAO.updateUser()");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int userWithdrawal(String seq) {
		
		try {
			
			String sql = "DELETE FROM USERS WHERE SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("UsersDAO.userWithdrawal()");
			e.printStackTrace();
		}
		
		return 0;
	}
	

	//shop.do에서 가게seq로 점주의 이름을 요청해요.
	public UsersDTO getShopUser(String seq) {
		
		try {
			String sql = "select  u.seq as seq, u.name as name from Users u\r\n"
					+ "inner join Shop s on u.seq=s.Users_seq and s.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();

			if (rs.next()) {
				
				UsersDTO dto = new UsersDTO();
				
				dto.setName(rs.getString("name"));
				dto.setSeq(rs.getString("seq"));
				
				return dto;
			}
		} catch (Exception e) {
			System.out.println("UsersDAO.getShopUser()");
			e.printStackTrace();
		}
		return null;
	}
	
	public UsersDTO getUserInfo(String seq) {
		
		try {
			
			String sql = "SELECT SEQ, NICKNAME, NAME, ID, EMAIL, PASSWORD, PHONE, AUTH, POINT FROM USERS WHERE SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();

			if (rs.next()) {

				UsersDTO dto = new UsersDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setNickname(rs.getString("nickname"));
				dto.setId(rs.getString("id"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setPhone(rs.getString("phone"));
				dto.setAuth(rs.getString("auth"));
				dto.setPoint(rs.getString("point"));

				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("UsersDAO.getUserInfo()");
			e.printStackTrace();
		}
		
		return null;
	}
	
}