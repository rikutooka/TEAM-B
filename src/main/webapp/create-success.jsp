<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者登録成功画面</title>
</head>
<body>
<h1>管理者の登録が完了いたしました</h1>
<form action="CreateUser" method="GET">
<p>${message}</p>
<p>ID: ${userID}</p>
<p>パスワード: ${password}</p>
<input type="submit" value="ログイン画面へ戻る">
</form>
</body>
</html>