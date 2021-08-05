package com.yumyum.customer.customerservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yumyum.dao.Cs_questionDAO;
import com.yumyum.dto.Cs_questionDTO;
import com.yumyum.dto.ShopDTO;

@WebServlet("/customer/customerservice/cs_question.do")
public class Cs_question extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("cs_subject_seq");
		String searchWord = req.getParameter("searchWord"); //검색어
		String SearchRange = "between 1 and 13";
		
		String isSearch = "n"; //검색여부
		
		//검색이면 y
		if(searchWord != null && !searchWord.equals("")) {
			isSearch = "y";
		}
		
		Cs_questionDAO dao = new Cs_questionDAO();
		
		//조회할 정보들을 해시맵에 담는다.
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("SearchRange", SearchRange);//검색범위
		map.put("searchWord", searchWord);
		map.put("isSearch", isSearch);
				
		
		//자주 묻는 질문의 내용과 답변을 받아온다.
		ArrayList<Cs_questionDTO> cslist = dao.getQuestion(seq);
		
		//질문을 받아 온다.
		ArrayList<Cs_questionDTO> list = dao.getSearchQuestion(map);
		
		req.setAttribute("cslist", cslist);
		req.setAttribute("map", map);
		req.setAttribute("list", list);
		
		/*
		 * System.out.println("검색후"+list); 
		 * System.out.println("검색전"+map);
		 */
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/customer/customerservice/cs_question.jsp");
		dispatcher.forward(req, resp);

	}

}
