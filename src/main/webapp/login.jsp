<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン</title>
</head>
<body>
	<h1>管理者ログイン</h1>
	<form action="UserLogin" metod="GET">
		<p>ID: <input type="text" name="user_id"></p>
		<p>パスワード: <input type="password" name="password"></p>
		<input type="submit" value="ログイン">
	</form>
<!--管理者作成ページ-->
	<form action="UserLogin" method="POST">
	<input type="submit" value="管理者を登録する">
	</form>
</body>
</html>