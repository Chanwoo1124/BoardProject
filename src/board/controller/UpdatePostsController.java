package board.controller;

import board.dao.PostDao;
import board.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/posts/edit")
public class UpdatePostsController extends HttpServlet {

    // 수정 폼 열기
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Integer userId = (Integer) request.getSession().getAttribute("userid");

        PostDao dao = new PostDao();
        Post post = null;
        try {
            post = dao.findById(id);
        } catch (SQLException ignore) { }

        // 글 없거나 로그인 안되어 있거나, 소유자 아님 → 권한 없음
        if (post == null || userId == null || userId.intValue() != post.getAuthorId()) {
            request.setAttribute("msg", "권한이 없습니다.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp").forward(request, response);
    }

    // 수정 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer userId = (Integer) request.getSession().getAttribute("userid");

        // 제목/내용 체크 간단히
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            request.setAttribute("msg", "제목/내용을 입력해주세요.");
            // 기존 글 다시 싣고 폼으로
            try { request.setAttribute("post", new PostDao().findById(id)); } catch (SQLException ignore) {}
            request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp").forward(request, response);
            return;
        }

        // DB 업데이트 (내 글만)
        int rows = 0;
        try {
            rows = new PostDao().update(id, userId == null ? -1 : userId, title, content);
        } catch (SQLException ignore) { }

        if (rows == 1) {
            // 성공 → 상세로
            response.sendRedirect(request.getContextPath() + "/posts/detail?id=" + id);
            return;
        }

        // 실패(권한 없음/오류) → 메시지 띄우고 폼으로
        request.setAttribute("msg", "권한이 없습니다.");
        try { request.setAttribute("post", new PostDao().findById(id)); } catch (SQLException ignore) {}
        request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp").forward(request, response);
    }
}

