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
import com.yumyum.dao.Menu_groupDAO;
import com.yumyum.dao.OrderlistDAO;
import com.yumyum.dao.Plus_priceDAO;
import com.yumyum.dao.ReviewDAO;
import com.yumyum.dao.Review_commentDAO;
import com.yumyum.dao.Review_menuDAO;
import com.yumyum.dao.Review_noticeDAO;
import com.yumyum.dao.ShopDAO;
import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.MenuDTO;
import com.yumyum.dto.Menu_groupDTO;
import com.yumyum.dto.Order_menuDTO;
import com.yumyum.dto.Plus_priceDTO;
import com.yumyum.dto.ReviewDTO;
import com.yumyum.dto.Review_commentDTO;
import com.yumyum.dto.Review_menuDTO;
import com.yumyum.dto.Review_noticeDTO;
import com.yumyum.dto.ShopDTO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/shop.do")
public class Shop extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");

		ShopDAO dao1 = new ShopDAO();
		Review_noticeDAO dao2 = new Review_noticeDAO();
		Plus_priceDAO dao3 = new Plus_priceDAO();
		UsersDAO dao4 = new UsersDAO();
		Review_menuDAO dao5 = new Review_menuDAO();
		ReviewDAO dao6 = new ReviewDAO();
		Menu_groupDAO dao7 = new Menu_groupDAO();
		MenuDAO dao8 = new MenuDAO();
		Review_commentDAO dao9 = new Review_commentDAO();
		OrderlistDAO dao10 = new OrderlistDAO();
		

		//가게의 이름,전화번호,설명,최소배달팁,가게주소,사업자등록번호를 받아온다.
		ShopDTO shopdto = dao1.getShop(seq);
		
		//가게 리뷰이벤트공지내용, 시작날짜, 종료날짜를 받아온다.
		Review_noticeDTO rvndto = dao2.getRevnotice(seq);
		
		//추가배달금, 행정동(여러개)을 받아온다.
		ArrayList<Plus_priceDTO> pplist = dao3.getPprice(seq);
		
		//점주의 이름을 받아온다.
		UsersDTO dto = dao4.getShopUser(seq);
		
		//리뷰이벤트의 서비스 품목들을 받아온다.
		ArrayList<Review_menuDTO> rvmlist = dao5.getReviewMenu(seq); 
		
		//소비자 리뷰의 내용, 등록날짜, 리뷰사진을 받아온다.
		ArrayList<ReviewDTO> rvlist = dao6.getReview(seq);
		
		//가게에 속한 메뉴그룹들을 받아온다.
		ArrayList<Menu_groupDTO> mglist = dao7.getMenuGroup(seq);
		
		//메뉴그룹에 속한 메뉴들을 받아온다.
		ArrayList<MenuDTO> mlist = dao8.getMenu(seq); 
		
		//리뷰에 대한 사장님답글을 받아온다.
		ArrayList<Review_commentDTO> rvclist = dao9.getReviewComment(seq);
		
		//사용자번호로 리뷰작성자의 주문내역을 받아온다.
		ArrayList<Order_menuDTO> omlist = dao10.getOrderlistDAO(seq);
		
		req.setAttribute("shopdto", shopdto);
		req.setAttribute("rvndto", rvndto);
		req.setAttribute("pplist", pplist);
		req.setAttribute("userdto", dto);
		req.setAttribute("rvmlist", rvmlist);
		req.setAttribute("rvlist", rvlist);
		req.setAttribute("mglist", mglist);
		req.setAttribute("mlist", mlist);
		req.setAttribute("rvclist", rvclist);
		req.setAttribute("omlist", omlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/yumyum/shop.jsp");
		dispatcher.forward(req, resp);

	}

}
