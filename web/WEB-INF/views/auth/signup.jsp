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
    <title>회원 가입</title>
</head>
<body>
    <h3>회원 가입</h3>
    ${msg}
<form action="singupcontroller" method="post">
    아이디:<input type="text" name = "id"><br>
    비밀번호:<input type="text" name = "pwd"><br>
    이름:<input type="text" name = "name"><br>
    <input type="submit" value="회원 가입">
</form>
</body>
</html>
