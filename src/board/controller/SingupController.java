package board.controller;

import board.dao.UserDao;
import board.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



@WebServlet("/singupcontroller")
public class SingupController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 값을 검증하기
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        if (id.isBlank() || pwd.isBlank() || name.isBlank()) {
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp");
            String msg = "아이디 또는 비밀번호 또는 이름이 입력되지 않았습니다";
            request.setAttribute("msg", msg);
            rd.forward(request, response);
            return;
        // 데이터 처리 기능 구현
        } else {
            User user = new User(id, pwd, name);
            UserDao userDao = new UserDao();
            //
            try {
                // 성공시 1 반환받고 로그인 페이지로
                int i = userDao.userInsert(user);
                if (i == 1) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }
                // 오류 날 시 간단히 한개로 처리 (서버오류, 중복 아이디, 데이터 길이 초과 등)
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("msg", "이미 사용 중인 아이디입니다.");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp");
                rd.forward(request, response);

            }
        }
    }

}

