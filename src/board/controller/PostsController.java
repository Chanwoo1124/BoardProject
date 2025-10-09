package board.controller;
import board.dao.PostDao;
import board.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/posts")
public class PostsController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        //페이지 수를 받음
        String ppage = request.getParameter("page");
        int page = Integer.parseInt(ppage);
        PostDao post = new PostDao();
        List<Post> posts;
        try {
            posts = post.findPage(page);
            request.setAttribute("posts",posts);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/list.jsp");
            rd.forward(request, response);
            return;
        } catch (SQLException e) {
            String msg = "오류가 발생하였습니다";
            request.setAttribute("msg", msg);
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
            return;
        }
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response){


    }
}










