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



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 값 검증
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            String msg = "제목 또는 내용을 입력해주세요";
            request.setAttribute("msg", msg);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/new.jsp");
            rd.forward(request, response);
            return;
        }

        // 로그인 사용자 (세션에서)
        Integer authorId = (Integer) request.getSession().getAttribute("userid");
        if (authorId == null) {
            request.setAttribute("msg", "로그인이 필요합니다.");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
            return;
        }

        // 데이터 처리
        PostDao postDao = new PostDao();
        try {
            int i = postDao.insert(authorId, title, content);
            if (i == 1) {
                response.sendRedirect(request.getContextPath() + "/posts?page=1");
                return;
            }
        } catch (SQLException e) {
            // 너무 디테일 말고 한 문구로만
            request.setAttribute("msg", "등록에 실패했습니다. 잠시 후 다시 시도해주세요.");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/new.jsp");
            rd.forward(request, response);
            return;
        }

        // 여기까지 왔다는 건 insert 결과가 1이 아님
        request.setAttribute("msg", "등록에 실패했습니다.");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/new.jsp");
        rd.forward(request, response);
    }

}










