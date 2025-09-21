package board.filter;


import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //톰캣에게 UTF-8 인코딩해라 알려줌
        req.setCharacterEncoding("UTF-8");
        //브라우저에게 UTF-8
        res.setCharacterEncoding("UTF-8");

        //res.setContentType("text/html; charset=UTF-8"); 모든 파일이 html이기때문에 굳이 안적어도 됨
        //doFilter
        chain.doFilter(req, res);
    }
}