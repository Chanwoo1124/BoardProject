package board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logincontroller")
public class LoginController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse  response){
        // 데이터 추출 id pwd 빈 값 일시 메세지넣고 포워드 해서 login.jsp


        // 꺼내온 데이터 db 비교시 맞을 시 로그인 틀릴시 반환  db 예외처리
        // userID 반환 받기




    }
}



