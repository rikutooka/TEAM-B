<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコ新規登録</title>
</head>
<body>
    <h2>タバコ新規登録</h2>
    <form action="ProductRegisterServlet" method="post">
        銘柄: <input type="text" name="cig_name"><br>
        タール: <input type="text" name="tar"><br>
        ニコチン: <input type="text" name="nicotine"><br>
        価格: <input type="text" name="price"><br>
        詳細: <input type="text" name="detail"><br>
        在庫: <input type="text" name="stock"><br>
        <input type="submit" value="登録">
    </form>
    <form action="productListMaster.jsp" method="get">
        <input type="submit" value="一覧へ">
    </form>
</body>
</html>