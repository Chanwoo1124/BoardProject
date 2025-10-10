<%--
  Created by IntelliJ IDEA.
  User: chan
  Date: 2025. 9. 20.
  Time: PM 7:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 작성</title>
</head>
<body>
<h3>게시판 작성</h3>

<form action="${pageContext.request.contextPath}/posts" method="post">
    제목<br>
    <input type="text" id="title" name="title"><br>

    내용<br>
    <textarea id="content" name="content" rows="5" cols="50"></textarea><br>

    <input type="submit" value="등록">
</form>
</body>

</html>
