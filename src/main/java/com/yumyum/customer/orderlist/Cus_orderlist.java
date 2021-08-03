package com.yumyum.customer.orderlist;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.OrderlistDAO;
import com.yumyum.dto.Order_menuDTO;
import com.yumyum.dto.OrderlistDTO;

@WebServlet("/customer/orderlist/cus_orderlist.do")
public class Cus_orderlist extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String seq = session.getAttribute("seq").toString();
		
		OrderlistDAO dao = new OrderlistDAO();

		// 페이징 처리
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 5; // 한 페이지당 출력한 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 가져올 게시물 시작 위치
		int end = 0; // 가져올 게시물 끝 위치
		int n = 0; // 페이지바 제작
		int loop = 0; // 페이지바 제작
		int blockSize = 10; // 페이지바 제작
		
		String page = req.getParameter("page");
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		ArrayList<OrderlistDTO> orderList = dao.getOrderlist(seq, begin+"", end+"");
		ArrayList<Order_menuDTO> orderMenuList = dao.getOrdermenu(seq);
		
		// 총 게시물 수 알아내기
		totalCount = dao.getTotalOrderlist(seq);
		
		// 총 페이지 수 알아내기
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		String pagebar = "<nav>\r\n"
				+ "			<ul class=\"pagination\">";

		loop = 1; // while 루프 변수
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 출력되는 페이지 번호
		
		if(n == 1) {
			pagebar += String.format(" <li class='disabled'><a href='#!' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li> ");
		} else {
			pagebar += String.format(" <li><a href='/yumyum/customer/orderlist/cus_orderlist.do?page=%d' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li> ", n-1);
		}
		
		if (totalPage == 0) {
			pagebar += " <li class='active'><a href='#!'>1</a></li> ";
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			if (n == nowPage) {
				pagebar += String.format(" <li class='active'><a href='#!'>%d</a></li> ", n);

			} else {
				pagebar += String.format(" <li><a href='/yumyum/customer/orderlist/cus_orderlist.do?page=%d'>%d</a></li> ", n, n);
			}
			
			loop++;
			n++;
		}

		if (n > totalPage) {
			pagebar += String.format(" <li class='disabled'><a href='#!' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li> ");
		} else {
			pagebar += String.format(" <li><a href='/yumyum/customer/orderlist/cus_orderlist.do?page=%d' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li> ", n);
		}
		
		pagebar += "</ul>\r\n"
				+ "		</nav>";
		
		req.setAttribute("orderList", orderList);
		req.setAttribute("orderMenuList", orderMenuList);
		
		req.setAttribute("pagebar", pagebar);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/customer/orderlist/cus_orderlist.jsp");
		dispatcher.forward(req, resp);
		
	}

}