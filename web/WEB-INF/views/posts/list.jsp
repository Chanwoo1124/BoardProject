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
    List<Post> posts = (List<Post>) request.getAttribute("posts");
    if (posts == null || posts.isEmpty()) {
        out.write("게시판 목록이 없습니다");
    } else {
        String ctx = request.getContextPath();
        for (Post p : posts) {
            out.write("<a href='" + ctx + "/posts/detail?id=" + p.getId() + "'>"
                    + p.getTitle() + "</a> | " + p.getId() + " | " + p.getCreatedDate() + "<br/>");
        }
    }
%>


</body>
</html>
