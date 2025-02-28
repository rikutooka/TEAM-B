<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコ新規登録</title>
<style>
    body {
         text-align: center;
    }
    h2 {
       margin-bottom: 20px;
    }
    table {
          margin: 0 auto;
    }
    
    
</style>
</head>
<body>
    <h2>タバコ新規登録</h2>

    <!-- エラーメッセージ表示 -->
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
        <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <form action="RegisterServlet" method="post">
      <table>
            <tr>
                <td>銘柄：</td>
                <td><input type="text" name="cig_name" required></td>
            </tr>
            <tr>
                <td>タール：</td>
                <td><input type="number" name="tar" step="0.1" required></td>
            </tr>
            <tr>
                <td>ニコチン：</td>
                <td><input type="number" name="nicotine" step="0.1" required></td>
            </tr>
            <tr>
                <td>価格：</td>
                <td><input type="number" name="price" required></td>
            </tr>
            <tr>
                <td>カテゴリー：</td>
                <td><input type="text" name="category" required></td>
            </tr>
            <tr>
                <td>フレーバー：</td>
                <td><input type="text" name="flavor" required></td>
            </tr>
            <tr>
                <td>詳細：</td>
                <td><input type="text" name="detail"></td>
            </tr>
            <tr>
                <td>在庫：</td>
                <td><input type="number" name="stock" required></td>
            </tr>
        </table>
        <input type="submit" value="登録">
    </form>
    
    <form action="search.jsp">
        <input type="submit" value="TOP">
    </form>
</body>
</html>
