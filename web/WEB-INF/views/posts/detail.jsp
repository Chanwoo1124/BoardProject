<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="board.model.Post" %>
<%
    Post p = (Post) request.getAttribute("post");
    Integer uid = (Integer) session.getAttribute("userid");
%>
<h3>게시글 상세</h3>

<% if (p != null) { %>
제목: <%= p.getTitle() %><br/>
번호: <%= p.getId() %><br/>
작성자: <%= p.getAuthorId() %><br/>
날짜: <%= p.getCreatedDate() %><br/>
<hr/>
<pre><%= p.getContent() %></pre>

<% if (uid != null && uid == p.getAuthorId()) { %>
<a href="<%= request.getContextPath() %>/posts/edit?id=<%= p.getId() %>">수정</a>
<a href="<%= request.getContextPath() %>/posts/delete?id=<%= p.getId() %>">삭제</a>
<% } %>

<% } else { %>
글이 없습니다.
<% } %>

<p><a href="<%= request.getContextPath() %>/posts?page=1">목록으로</a></p>
