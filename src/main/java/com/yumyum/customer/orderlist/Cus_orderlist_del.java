package com.yumyum.customer.orderlist;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.OrderlistDAO;
import com.yumyum.dto.Order_menuDTO;
import com.yumyum.dto.OrderlistDTO;

@WebServlet("/customer/orderlist/cus_orderlist_del.do")
public class Cus_orderlist_del extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		OrderlistDAO dao = new OrderlistDAO();
		
		int result = dao.delOrderlist(seq);

		if (result == 1) {
			resp.sendRedirect("/yumyum/customer/orderlist/cus_orderlist.do");
		} else {
			resp.sendRedirect("/yumyum/customer/orderlist/cus_orderlist.do");
		}

	}

}