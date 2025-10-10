<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="board.model.Post" %>
<%
    Post p = (Post) request.getAttribute("post");
%>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
    <h3>글 수정</h3>
    <form action="${pageContext.request.contextPath}/posts/edit?id=<%=p.getId()%>" method="post">
        제목<br>
        <input type="text" name="title" value="<%=p.getTitle()%>"><br>
        내용<br>
        <textarea name="content" rows="10" cols="60"><%=p.getContent()%></textarea><br>
        <input type="submit" value="수정">
    </form>
    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null && !msg.isEmpty()) {
    %>
    <p><%= msg %></p>
    <%
        }
    %>

</body>
</html>
