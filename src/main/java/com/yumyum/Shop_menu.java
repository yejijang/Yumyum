package com.yumyum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.MenuDAO;
import com.yumyum.dto.MenuDTO;

@WebServlet("/shop_menu.do")
public class Shop_menu extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		String menuseq = req.getParameter("menuseq");
		
		MenuDAO dao = new MenuDAO();
		
		//메뉴그룹에 속한 메뉴들을 받아온다.
		ArrayList<MenuDTO> mlist = dao.getMenu(seq);
		
		req.setAttribute("mlist", mlist);
		req.setAttribute("menuseq", menuseq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/shop_menu.jsp");
		dispatcher.forward(req, resp);

	}
}