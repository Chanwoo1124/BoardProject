package board.controller;

import board.dao.PostDao;
import board.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/posts/delete")
public class DeletePostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //쿼리값 받아오기
        int id = Integer.parseInt(request.getParameter("id"));
        Integer userId = (Integer) request.getSession().getAttribute("userid");
        //get요청 예외처리
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //삭제 메소드 실행
        PostDao postdao = new PostDao();
        int result = 0;
        try {
            result = postdao.delete(id, userId);
        } catch (SQLException e) {

        }
        // resut 값에 따른 결과 처리
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/posts?page=1");
        } else {
            request.setAttribute("msg", "삭제 권한이 없습니다.");
            request.getRequestDispatcher("/WEB-INF/views/posts/detail.jsp").forward(request, response);
        }
    }
}
