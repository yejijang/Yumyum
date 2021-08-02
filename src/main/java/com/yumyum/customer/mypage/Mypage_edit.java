package com.yumyum.customer.mypage;

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

@WebServlet("/customer/mypage/mypage_edit.do")
public class Mypage_edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String seq = session.getAttribute("seq").toString();
		
		UsersDAO dao = new UsersDAO();
		
		UsersDTO dto = dao.getMypageEdit(seq);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/customer/mypage/mypage_edit.jsp");
		dispatcher.forward(req, resp);

	}
}