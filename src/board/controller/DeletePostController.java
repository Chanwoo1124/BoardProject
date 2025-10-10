package board.controller;

import board.dao.PostDao;

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

        int id = Integer.parseInt(request.getParameter("id"));
        Integer userId = (Integer) request.getSession().getAttribute("userid");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int result = 0;
        try {
            result = new PostDao().delete(id, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/posts?page=1");
        } else {
            request.setAttribute("msg", "삭제 권한이 없습니다.");
            request.getRequestDispatcher("/WEB-INF/views/posts/detail.jsp").forward(request, response);
        }
    }
}
