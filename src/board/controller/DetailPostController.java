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

        String sid = req.getParameter("id");
        if (sid == null || sid.isBlank()) { resp.sendError(400); return; }

        int id;
        try { id = Integer.parseInt(sid); }
        catch (NumberFormatException e) { resp.sendError(400); return; }

        Post post;
        try { post = new PostDao().findById(id); }
        catch (Exception e) { resp.sendError(500); return; }

        if (post == null) { resp.sendError(404); return; }

        req.setAttribute("post", post);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/posts/detail.jsp");
        rd.forward(req, resp);
    }
}
