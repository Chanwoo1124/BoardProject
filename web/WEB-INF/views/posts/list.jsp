<%@ page import="java.util.List" %>
<%@ page import="board.model.Post" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
<h3>게시판 목록</h3>

<%
    // 게시판글 리스트 검증 후 <a> 출력하기
    List<Post> posts = (List<Post>) request.getAttribute("posts");
    if (posts == null || posts.isEmpty()) {
        out.write("게시판 목록이 없습니다");
    } else {
        String contextpath = request.getContextPath();
        // 클릭시 쿼리스트링으로 id값을 보냄 DetailPostController클래스
        for (Post p : posts) {
            out.write("<a href='" + contextpath + "/posts/detail?id=" + p.getId() + "'>"
                    + p.getTitle() + "</a> | " + p.getId() + " | " + p.getCreatedDate() + "<br/>");
        }
    }
%>

<%
    int pageNo = 1;
    try {
        pageNo = Integer.parseInt(request.getParameter("page"));
    } catch (Exception ignore) {
        out.write("오류 발생");
    }
    String contextpath = request.getContextPath();
%>
<div>
    <% if (pageNo > 1) { %>
    <a href="<%=contextpath%>/posts?page=<%=pageNo-1%>">이전</a>
    <% } %>
    <a href="<%=contextpath%>/posts?page=<%=pageNo+1%>">다음</a>
</div>

<a href="new">작성</a>



</body>
</html>
