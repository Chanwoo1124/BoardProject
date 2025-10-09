package board.controller;

import board.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/logincontroller")
public class LoginController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        //데이터 추출 후 검증
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        if (id.isBlank() || pwd.isBlank()) {
            String msg = "아이디 또는 비밀번호를 입력해주세요.";
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/login.jsp");
            request.setAttribute("msg",msg);
            rd.forward(request,response);
            return;
        }
        else {
            UserDao user = new UserDao();
            int i = 0;
            try {
                i = user.userFind(id, pwd);
            } catch (SQLException e) {
                request.setAttribute("msg", "오류 발생 잠시 후에 다시 시도해주세요");
                RequestDispatcher rd =request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);
                return;
            }
            if (i > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("userid",i);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/list.jsp");
                rd.forward(request,response);
            }else{
                request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request,response);
            }

        }





    }
}



