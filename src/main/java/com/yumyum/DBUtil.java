package com.yumyum;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	public static Connection open() {
		
		// 자바(JDBC)를 사용해서 오라클에 접속하기

		// 1. DB 접속
		// - Connection 클래스
		Connection conn = null;

		// 2. 연결 문자열 생성
		// - 접속에 필요한 정보로 구성된 문자열
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "yumyum";
		String pw = "java1234";

		try {
			// 3. 설치한 JDBC 드라이버 로딩(ojdbc6.jar)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 4. 접속
			// - Connection 객체(new Connection()) + 오라클 접속
			// - 연결이 됨과 동시에 연결 정보를 가지고 있는 Connection 객체를 반환
			conn = DriverManager.getConnection(url, id, pw); // ***** 자바와 오라클이 연결(접속 o)
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection open(String server, String id, String pw) {

		// 자바(JDBC)를 사용해서 오라클에 접속하기

		// 1. DB 접속
		// - Connection 클래스
		Connection conn = null;

		// 2. 연결 문자열 생성
		// - 접속에 필요한 정보로 구성된 문자열
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";

		try {
			// 3. 설치한 JDBC 드라이버 로딩(ojdbc6.jar)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 4. 접속
			// - Connection 객체(new Connection()) + 오라클 접속
			// - 연결이 됨과 동시에 연결 정보를 가지고 있는 Connection 객체를 반환
			conn = DriverManager.getConnection(url, id, pw); // ***** 자바와 오라클이 연결(접속 o)

			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
