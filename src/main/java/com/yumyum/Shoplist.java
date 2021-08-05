package com.yumyum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		String searchWord = req.getParameter("searchWord"); //검색어
		
		String isSearch = "n"; //검색여부
		
		//검색이면 y
		if(searchWord != null && !searchWord.equals("")) {
			isSearch = "y";
		}
		
		ShopDAO dao = new ShopDAO();
		
		CheckLocation cl = new CheckLocation();
		
		//소비자가 입력(설정)한 주소를 전달하여 좌표를 받아 온다.
		ShopDTO location = cl.getLocation(address);
		
		//조회할 정보들을 해시맵에 담는다.
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("seq", seq);
		map.put("lon", location.getLon());
		map.put("lat", location.getLat());
		map.put("searchWord", searchWord);
		map.put("isSearch", isSearch);
		
		//가게 목록을 받아 온다.
		ArrayList<ShopDTO> list = dao.getListshop(map);
		
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("address", address);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/shoplist.jsp");
		dispatcher.forward(req, resp);

	}

}
