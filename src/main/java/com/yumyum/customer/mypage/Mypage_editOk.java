package com.yumyum.customer.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/customer/mypage/mypage_editok.do")
public class Mypage_editOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UsersDTO dto = new UsersDTO();

		dto.setSeq(req.getParameter("seq"));
		dto.setNickname(req.getParameter("nickname"));
		dto.setEmail(req.getParameter("email"));
		dto.setPassword(req.getParameter("newpw"));
		dto.setPhone(req.getParameter("phone"));

		UsersDAO dao = new UsersDAO();

		int result = dao.updateUser(dto);

		if (result == 1) {
			resp.sendRedirect("/yumyum/customer/mypage/mypage.do");
		} else {
			resp.sendRedirect("/yumyum/customer/mypage/mypage_edit.do");
		}

	}
}