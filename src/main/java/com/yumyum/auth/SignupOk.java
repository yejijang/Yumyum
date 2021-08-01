package com.yumyum.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.SignupDAO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/auth/signupok.do")
public class SignupOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UsersDTO dto = new UsersDTO();

		dto.setName(req.getParameter("name"));
		dto.setId(req.getParameter("id"));
		dto.setNickname(req.getParameter("nickname"));
		dto.setEmail(req.getParameter("email"));
		dto.setPassword(req.getParameter("pw"));
		dto.setPhone(req.getParameter("phone"));
		dto.setAuth(req.getParameter("auth"));

		SignupDAO dao = new SignupDAO();
		int result = 0;

		if (req.getParameter("auth").equals("C")) {
			result = dao.newCustomer(dto);
		} else {
			result = dao.newOwner(dto);
		}

		if (result == 1) {
			resp.sendRedirect("/yumyum/main.do");
		} else {
			resp.sendRedirect("/yumyum/auth/signup.do");
		}

	}

}