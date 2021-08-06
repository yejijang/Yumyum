package com.yumyum.owner.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.ShopDAO;
import com.yumyum.dto.CategoryDTO;
import com.yumyum.dto.ShopDTO;

@WebServlet("/owner/shop/shop_add.do")
public class Shop_add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String users_seq = session.getAttribute("seq").toString();
		
		ShopDAO dao = new ShopDAO();
		
		//select 박스에 카테고리 목록 출력
		ArrayList<CategoryDTO> list =  dao.getCategoryList();
		
		//점주의 입점 승인 상태 확인 (R: 대기, Y: 완료, N: 거부)
		ShopDTO dto = dao.getAuth(users_seq);
		
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/owner/shop/shop_add.jsp");
		dispatcher.forward(req, resp);

	}

}
