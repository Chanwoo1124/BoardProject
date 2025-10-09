<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="board.model.Post" %>
<%
    Post p = (Post) request.getAttribute("post");
%>
<h3>게시글 상세</h3>
<% if (p != null) { %>
제목: <%= p.getTitle() %><br/>
번호: <%= p.getId() %><br/>
작성자: <%= p.getAuthorId() %><br/>
날짜: <%= p.getCreatedDate() %><br/>
<hr/>
<pre><%= p.getContent() %></pre>
<% } else { %>
글이 없습니다.
<% } %>
<p><a href="<%= request.getContextPath() %>/posts?page=1">목록으로</a></p>