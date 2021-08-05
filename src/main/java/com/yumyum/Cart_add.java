package com.yumyum;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.CartDAO;
import com.yumyum.dao.SignupDAO;
import com.yumyum.dto.CartDTO;

@WebServlet("/cart_add.do")
public class Cart_add extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String shop_seq = req.getParameter("shop_seq");
		String users_seq = req.getParameter("users_seq");
		String menu_seq = req.getParameter("menu_seq");
		String menu_option_seq = req.getParameter("menu_option_seq");
		String cnt = req.getParameter("quantity");

		CartDAO dao = new CartDAO();
		
		CartDTO dto = new CartDTO();
		dto.setUsers_seq(users_seq);
		dto.setMenu_seq(menu_seq);
		dto.setMenu_option_seq(menu_option_seq);
		dto.setCnt(Integer.parseInt(cnt));
		
		int result = dao.addCart(dto);

		if (result == 1) {
			resp.sendRedirect("/yumyum/shop.do?seq=" + shop_seq);
		} else {
			resp.sendRedirect("/yumyum/shop.do?seq=" + shop_seq);
		}

	}

}