package com.yumyum.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yumyum.dao.UsersDAO;
import com.yumyum.dto.UsersDTO;

@WebServlet("/auth/findpwok.do")
public class FindpwOk extends HttpServlet {

	@Override	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
        String id = req.getParameter("id");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        
        UsersDAO dao = new UsersDAO();
		
		UsersDTO dto = new UsersDTO();
		dto.setId(id);
		dto.setPhone(phone);
		dto.setEmail(email);

		UsersDTO resultDTO = dao.findpw(dto);
		
		PrintWriter writer = resp.getWriter();
		
		
		if(resultDTO != null) {
			
			//mail server 설정		
			String host = "smtp.gmail.com";
			String user = "quddms6391@gmail.com";
			String password = "@@gogov3";
	        
	        //메일 받을 주소
	        String to_email = email;
	        
	        //SMTP 서버 정보를 설정한다.
	        Properties props = new Properties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", 465);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");

	        
	        //임시 비밀번호 생성
	        StringBuffer temp = new StringBuffer();
	        Random rnd = new Random();
	        
	        for(int i = 0; i < 10; i++) {
	        	
	            int rIndex = rnd.nextInt(3);
	            
	            switch (rIndex) {
	            case 0:
	                // a-z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	                break;
	            case 1:
	                // A-Z
	                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	                break;
	            case 2:
	                // 0-9
	                temp.append((rnd.nextInt(10)));
	                break;
	            }
	        }
	        
	        String AuthenticationKey = temp.toString();
	        System.out.println(AuthenticationKey);
	        
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });
	        
	        //email 전송
	        try {
	        	
	            MimeMessage msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(user, "yumyum"));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
	            
	            //메일 제목
	            msg.setSubject("안녕하세요. 얌얌 인증 메일입니다.");
	            //메일 내용
	            msg.setText("임시 비밀번호는 " + temp + " 입니다.\n 변경된 비밀번호로 로그인 해주시기 바랍니다.");
	            //메일 전송
	            Transport.send(msg);
	            System.out.println("이메일 발송 완료");
	            
	            //비밀번호를 메일로 발송한 임시비밀번호로 업데이트
	            int result = dao.pwEdit(resultDTO.getSeq(), AuthenticationKey); //1(성공), 0(실패)
	            
	            if(result == 1) {
	            	
	            	writer.print("<script>");
		            writer.print("alert('이메일 발송 완료');");
					writer.print("location.href='/yumyum/auth/login.do';");
					
	            }
	            
	        } catch (Exception e) {
	        	
	        	writer.print("<script>");
	            writer.print("alert('이메일 발송 실패!');");
	            writer.print("history.back();");
	            writer.print("</script>");
	        	System.out.println("이메일 발송 실패!");
	            e.printStackTrace();
	            
	        }
	        
			
		} else {
			
			writer.print("<script>");
			writer.print("alert('일치하는 정보가 없습니다.');");
			writer.print("history.back();");
			
		}
		
		writer.print("</script>");
		writer.close();
	}

}
