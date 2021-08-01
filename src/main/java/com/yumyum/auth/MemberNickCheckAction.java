package com.yumyum.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.UsersDAO;

@WebServlet("/auth/membernickcheckaction.do")
public class MemberNickCheckAction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nick = req.getParameter("nick");
		
		UsersDAO dao = new UsersDAO();

		boolean result = dao.duplicateNickCheck(nick);

		resp.setContentType("text/html;charset=euc-kr");
		PrintWriter out = resp.getWriter();
		
		if (result)
			out.println("0"); // 아이디 중복
		else
			out.println("1");
		
		out.close();
	}
}