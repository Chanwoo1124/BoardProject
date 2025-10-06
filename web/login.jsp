<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
 <h3>로그인</h3>
 <form action="logincontroller" method="post">
     id: <input type="text" name ="id"><br>
     pwd: <input type="text" name ="pwd"><br>
     <input type="submit" value ="로그인">
 </form>
 <a href="singupcontroller">회원가입</a>
</body>
</html>
