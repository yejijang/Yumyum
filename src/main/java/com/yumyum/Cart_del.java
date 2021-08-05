package com.yumyum;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.CartDAO;

@WebServlet("/cart_del.do")
public class Cart_del extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		CartDAO dao = new CartDAO();
		
		dao.delCart(seq);

		resp.sendRedirect("/yumyum/cart.do");
		
	}
}