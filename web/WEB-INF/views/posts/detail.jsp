<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="board.model.Post" %>
<%
    Post p = (Post) request.getAttribute("post");
    Integer uid = (Integer) session.getAttribute("userid");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>게시글 상세</h3>
    <%--게시판 상세내용  출력--%>
    <% if (p != null) { %>
    제목: <%= p.getTitle() %><br/>
    번호: <%= p.getId() %><br/>
    작성자: <%= p.getAuthorId() %><br/>
    날짜: <%= p.getCreatedDate() %><br/>
    <hr/>
    <pre><%= p.getContent() %></pre>



    <%--세션에 저장되있는 아이디와 게시판 글 작성자가 같을시 수정, 삭제 태그 보임--%>
    <% if (uid != null && uid == p.getAuthorId()) { %>
    <%--updatecontroller 실행--%>
    <a href="<%= request.getContextPath() %>/posts/edit?id=<%= p.getId() %>">수정</a>
    <%--deletecontroller 실행--%>
    <a href="<%= request.getContextPath() %>/posts/delete?id=<%= p.getId() %>">삭제</a>
    <% } %>
    <% } %>

</body>
</html>
