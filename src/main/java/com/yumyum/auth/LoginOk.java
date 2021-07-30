package com.yumyum.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/auth/loginok.do")
public class LoginOk extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String password = req.getParameter("password");
	

		UsersDAO dao = new UsersDAO();

		UsersDTO dto = new UsersDTO();
		dto.setId(id);
		dto.setPassword(password);

		UsersDTO result = dao.login(dto);

		if (result != null) {
			// 인증 -> 티켓 발급
			HttpSession session = req.getSession();

			session.setAttribute("id", result.getId()); // 인증티켓
			
			session.setAttribute("seq", result.getSeq());
			session.setAttribute("name", result.getName());
			session.setAttribute("auth", result.getAuth());
			
			resp.sendRedirect("/yumyum/main.do");
		} else {
			// 실패
			resp.sendRedirect("/yumyum/auth/login.do");
		}
	}

}