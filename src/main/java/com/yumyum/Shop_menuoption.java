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
import com.yumyum.dto.Menu_optionDTO;

@WebServlet("/shop_menuoption.do")
public class Shop_menuoption extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		String menuseq = req.getParameter("menuseq");
		
		MenuDAO dao = new MenuDAO();
		
		// 메뉴에 속한 메뉴옵션들을 받아온다.
		ArrayList<Menu_optionDTO> oplist = dao.getMenuOption(menuseq);
				
		req.setAttribute("oplist", oplist);
		req.setAttribute("menuseq", menuseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/shop_menuoption.jsp");
		dispatcher.forward(req, resp);

	}
}