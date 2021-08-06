package com.yumyum.owner.shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yumyum.dao.ShopDAO;
import com.yumyum.dto.ShopDTO;

@WebServlet("/owner/shop/shop_addok.do")
public class Shop_addOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		String users_seq = session.getAttribute("seq").toString();
		
		PrintWriter writer = resp.getWriter();
		
		//테스트 시 로컬 자신의 로컬 경로를 입력 & files 디렉토리도 생성 필요
		String savePath = "D:\\jspproject\\Yumyum\\src\\main\\webapp\\files";
		
		//업로드 파일의 최대 크기 지정
		int size = 1024 * 1024 * 100; //100MB
		
		try {
			
			MultipartRequest multi = new MultipartRequest(req, savePath, size, "utf-8", new DefaultFileRenamePolicy());
			
			String name = multi.getParameter("name");
			String category = multi.getParameter("category");
			String phone = multi.getParameter("phone");
			String registration = multi.getParameter("registration");
			String address = multi.getParameter("address");
			
			// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
			String file1 = multi.getFilesystemName("file1");
			String file2 = multi.getFilesystemName("file2");
			 
			// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
			String m_fileFullPath1 = savePath + "/" + file1;
			String m_fileFullPath2 = savePath + "/" + file2;
			
			ShopDAO dao = new ShopDAO();
			ShopDTO dto = new ShopDTO();
			
			dto.setUsers_seq(users_seq);
			dto.setCategory_seq(category);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setAddress(address);
			dto.setRegistration(registration);
			dto.setFile1(m_fileFullPath1);
			dto.setFile2(m_fileFullPath2);
			
			//db에 insert
			int result = dao.addShop(dto);
			
			if(result == 1) { 
				writer.print("<script>");
				writer.print("alert('신청 완료!');");
				writer.print("location.href='/yumyum/owner/shop/shop_add.do';");
				writer.print("</script>");
				writer.close();
			}
			 
			
		} catch (Exception e) {
			writer.print("<script>");
			writer.print("alert('파일 업로드 실패!');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
			System.out.println("파일 업로드 실패!");
			System.out.println(e);
		}

	}

}