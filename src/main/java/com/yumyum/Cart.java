package com.yumyum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.CartDAO;
import com.yumyum.dto.CartDTO;
import com.yumyum.dto.ShopDTO;

@WebServlet("/cart.do")
public class Cart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String seq = session.getAttribute("seq").toString();
		
		CartDAO dao = new CartDAO();
		
		ArrayList<ShopDTO> shoplist = dao.cartShopList(seq);
		ArrayList<CartDTO> menulist = dao.cartMenuList(seq);
		
		req.setAttribute("shoplist", shoplist);
		req.setAttribute("menulist", menulist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/cart.jsp");
		dispatcher.forward(req, resp);

	}

}
