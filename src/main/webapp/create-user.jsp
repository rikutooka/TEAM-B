<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者アカウント作成</title>
</head>
<body>
<h1>新しい管理者を作成します</h1>
<% String message = (String) request.getAttribute("message"); %>
<% if (message != null) { %>
    <p><%= message %></p>
<% } %>
<form action="CreateUser" method="POST">
<p>IDを入力してください:<input type="text" name="userID"></p>
<p>パスワードを入力してください:<input type="text" name="password"></p>
<input type="submit" value="作成する">
</form>
</body>
</html>