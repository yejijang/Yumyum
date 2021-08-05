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

import com.yumyum.dao.NoticeDAO;
import com.yumyum.dto.Cs_questionDTO;
import com.yumyum.dto.NoticeDTO;


@WebServlet("/noticelist.do")
public class Noticelist extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String searchWord = req.getParameter("searchWord"); //검색어
		
		String isSearch = "n"; //검색여부
		
		//검색이면 y
		if(searchWord != null && !searchWord.equals("")) {
			isSearch = "y";
		} else {
			searchWord = "";
		}
		
		
		//페이징 처리
		// -> 보고 싶은 페이지를 정하기 위한 처리
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수
		int pageSize = 5;		//한 페이지당 출력할 게시물 수
		int totalPage = 0;		//총 페이지 수
		int begin = 0;			//가져올 게시물 시작 위치
		int end = 0;			//가져올 게시물 끝 위치
		int n = 0;				//페이지바 제작
		int loop = 0;			//페이지바 제작
		int blockSize = 5;		//페이지바 제작

		
		String page = req.getParameter("page");
		
		if(page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("begin", begin + "");
		map.put("end", end + "");
		
		map.put("searchWord", searchWord);
		map.put("isSearch", isSearch);		
				
		NoticeDAO dao = new NoticeDAO();
		
		totalCount = dao.getTotalCount(map);
		
		//총 페이지 수 알아내기
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		//System.out.println(totalPage);
		
		
		String pagebar = "<nav>\r\n"
				+ "			<ul class=\"pagination\">";

		loop = 1; //while 루프 변수
		n = ((nowPage - 1) / blockSize) * blockSize + 1; //출력되는 페이지 번호
		
		if(n == 1) {
			pagebar += String.format(" <li class='disabled'><a href='#!' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li> ");
		} else {
			pagebar += String.format(" <li><a href='/yumyum/noticelist.do?page=%d' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li> ", n-1);
		}
		
		if(totalPage == 0) {
			pagebar += String.format(" <li class='active'><a href='#!'>1</a></li> ");
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			if( n == nowPage) {
				pagebar += String.format(" <li class='active'><a href='#!'>%d</a></li> ", n);
			} else {
				pagebar += String.format(" <li><a href='/yumyum/noticelist.do?page=%d&searchWord=%s'>%d</a></li> ", n, searchWord, n);
			}
			
			loop++;
			n++;
		}
		
		if(n > totalPage) {
			pagebar += String.format(" <li class='disabled'><a href='#!' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li> ");	
		} else {
			pagebar += String.format(" <li><a href='/yumyum/noticelist.do?page=%d' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li> ", n);
		}
		
		pagebar += "</ul>\r\n"
				+ "		</nav>";

		
		

		
		
		ArrayList<NoticeDTO> list = dao.getNoticeList(map);
		
		
		req.setAttribute("list", list);
		req.setAttribute("map", map); //*****
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", pagebar);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/noticelist.jsp");
		dispatcher.forward(req, resp);

	}

}
