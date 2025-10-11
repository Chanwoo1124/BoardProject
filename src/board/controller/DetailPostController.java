package board.controller;

import board.dao.PostDao;
import board.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/posts/detail")
public class DetailPostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //쿼리스트링으로 받은 값 출력 후 find 메소드 실행
        String idd = req.getParameter("id");
        int id = Integer.parseInt(idd);

        Post post;
        // detail 메소드 실행 후 post받아오기
        try {
            post = new PostDao().detailPost(id);
        } catch (Exception ignore) {
            req.setAttribute("msg", "오류가 발생했습니다.");
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
            return;
        }

        //반환받은 값 detail.jsp출력
        req.setAttribute("post", post);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/posts/detail.jsp");
        rd.forward(req, resp);
    }
}
