package com.yumyum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.ShopDAO;
import com.yumyum.dto.ShopDTO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/shoplist.do")
public class Shoplist extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("category");
		String address = req.getParameter("address");
		
		ShopDAO dao = new ShopDAO();
		
		CheckLocation cl = new CheckLocation();
		
		//주소를 전달하여 좌표를 받아 온다.
		ShopDTO location = cl.getLocation(address);
		
		//해당가게들을 받아 온다.
		ArrayList<ShopDTO> list = dao.getListshop(seq, location.getLon(), location.getLat());
		
		
		req.setAttribute("list", list);
		req.setAttribute("address", address);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/shoplist.jsp");
		dispatcher.forward(req, resp);

	}

}
