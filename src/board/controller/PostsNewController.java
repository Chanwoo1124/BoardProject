package board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/new")
public class PostsNewController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //쿠키 꺼내와서 값이 있으면 로그인 상태 없으면 로그아웃 상태
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        if (userid == null){
            String msg = "로그인 해주세요";
            request.setAttribute("msg",msg);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request,response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/new.jsp");
            rd.forward(request,response);
        }
    }
}
