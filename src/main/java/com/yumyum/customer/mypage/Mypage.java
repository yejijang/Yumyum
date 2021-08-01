package com.yumyum.customer.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.Customer_couponDAO;
import com.yumyum.dao.OrderlistDAO;
import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.Customer_couponDTO;
import com.yumyum.dto.OrderlistDTO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/customer/mypage/mypage.do")
public class Mypage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String seq = session.getAttribute("seq").toString();
		
		UsersDAO dao1 = new UsersDAO();
		Customer_couponDAO dao2 = new Customer_couponDAO();
		OrderlistDAO dao3 = new OrderlistDAO();
		
		//닉네임, 등급, 포인트, 얌얌혜택 종료일, 얌얌혜택 잔여횟수, 누적 결제금액을 받아 온다.
		UsersDTO dto = dao1.getMypage(seq);
		
		//사용 가능 쿠폰을 받아 온다.
		ArrayList<Customer_couponDTO> list = dao2.getListCoupon(seq);
		
		//카테고리별 주문 수를 받아 온다.
		ArrayList<OrderlistDTO> list2 = dao3.getListCategoryCnt(seq);
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);
		req.setAttribute("list2", list2);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/customer/mypage/mypage.jsp");
		dispatcher.forward(req, resp);

	}

}
