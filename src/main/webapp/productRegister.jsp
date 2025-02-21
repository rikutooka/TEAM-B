<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコ新規登録</title>
</head>
<body>
    <h2>タバコ新規登録</h2>

    <!-- エラーメッセージ表示 -->
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <form action="RegisterServlet" method="post">
        銘柄: <input type="text" name="cig_name" required><br>
        タール: <input type="number" name="tar" step="0.1" required><br> <!-- 小数入力対応 -->
        ニコチン: <input type="number" name="nicotine" step="0.1" required><br> <!-- 小数入力対応 -->
        価格: <input type="number" name="price" required><br>
        詳細: <input type="text" name="detail"><br>
        在庫: <input type="number" name="stock" required><br>
        <input type="submit" value="登録">
    </form>
    
    <form action="productListMaster.jsp" method="get">
        <input type="submit" value="一覧へ">
    </form>
</body>
</html>
