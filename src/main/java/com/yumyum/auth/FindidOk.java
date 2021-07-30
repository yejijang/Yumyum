package com.yumyum.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/auth/findidok.do")
public class FindidOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		
		UsersDAO dao = new UsersDAO();
		
		UsersDTO dto = new UsersDTO();
		dto.setName(name);
		dto.setPhone(phone);

		UsersDTO resultDTO = dao.findid(dto);
		
		PrintWriter writer = resp.getWriter();
		writer.print("<script>");
		
		
		if(resultDTO != null) {
			
			writer.print("alert('회원님의 아이디는 " + resultDTO.getId() + " 입니다.');");
			writer.print("location.href='/yumyum/auth/login.do';");
			
		} else {
			
			writer.print("alert('입력하신 정보와 일치하는 아이디가 없습니다.');");
			writer.print("history.back();");
			
		}
		
		writer.print("</script>");
		writer.close();
		
	}

}
