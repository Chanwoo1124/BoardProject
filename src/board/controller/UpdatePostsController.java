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

    // 수정 페이지 열기
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //쿼리스트링 값 가져오기
        int id = Integer.parseInt(request.getParameter("id"));
        Integer userId = (Integer) request.getSession().getAttribute("userid");

        PostDao dao = new PostDao();
        Post post = null;
        // id로 게시판 글 찾아오기
        try {
            post = dao.detailPost(id);
        } catch (SQLException ignore) {

        }

        // 글, 로그인 , 작성자 비교하여서 아니면 권한 없습니다 출력
        if (post == null || userId == null || userId == null && userId.equals(post.getAuthorId())) {
            request.setAttribute("msg", "권한이 없습니다.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp").forward(request, response);
    }

    // 수정
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //값 추출
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer userId = (Integer) request.getSession().getAttribute("userid");

       // 빈값있는지 확인
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            request.setAttribute("msg", "제목/내용을 입력해주세요.");
            RequestDispatcher rdE = request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp");
            rdE.forward(request,response);
            return;
        }

        // 게시판 상세 정보 받아오기
        PostDao post = new PostDao();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/edit.jsp");
        try {
            request.setAttribute("post", post.detailPost(id));
        }
        catch (SQLException ignore) {
            request.setAttribute("msg","오류 발생");
            rd.forward(request,response);
            return;
        }

        // db 게시글 등록
        int i = 0;
        try {
            i = post.update(id, userId, title, content);
        } catch (SQLException e) {
            request.setAttribute("msg", "오류 발생");
            rd.forward(request, response);
            return;
        }

        if (i == 1) {
            // 성공시 수정한 글로
            response.sendRedirect(request.getContextPath() + "/posts/detail?id=" + id);
            return;
        }

    }
}

