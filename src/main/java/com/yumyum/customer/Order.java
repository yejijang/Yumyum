package com.yumyum.customer;

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
import com.yumyum.dao.Customer_couponDAO;
import com.yumyum.dao.Review_menuDAO;
import com.yumyum.dao.Review_noticeDAO;
import com.yumyum.dao.ShopDAO;
import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.CartDTO;
import com.yumyum.dto.Customer_couponDTO;
import com.yumyum.dto.Review_menuDTO;
import com.yumyum.dto.ShopDTO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/customer/order.do")
public class Order extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String users_seq = session.getAttribute("seq").toString();
		
		String seq = req.getParameter("seq");
		
		UsersDAO dao = new UsersDAO();
		Customer_couponDAO ccoupondao = new Customer_couponDAO();
		Review_noticeDAO revnoticedao = new Review_noticeDAO();
		Review_menuDAO revmenudao = new Review_menuDAO();
		ShopDAO shopdao = new ShopDAO();
		CartDAO cartdao = new CartDAO();
		
		UsersDTO user = dao.getUserInfo(users_seq);
		ArrayList<Customer_couponDTO> couponList = ccoupondao.getOrderCouponList(users_seq);
		int revnotice = revnoticedao.checkRevNotice(seq);
		ArrayList<Review_menuDTO> revmenulist = revmenudao.getOrderRevMenu(seq);
		int shopTip = shopdao.getShopTip(seq);
		ArrayList<CartDTO> menulist = cartdao.cartMenuList(users_seq);
		
		req.setAttribute("seq", seq);
		req.setAttribute("user", user);
		req.setAttribute("couponList", couponList);
		req.setAttribute("revnotice", revnotice);
		req.setAttribute("revmenulist", revmenulist);
		req.setAttribute("shopTip", shopTip);
		req.setAttribute("menulist", menulist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/customer/order.jsp");
		dispatcher.forward(req, resp);

	}
}