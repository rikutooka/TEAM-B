<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコの詳細表示</title>
</head>
<body>
<h1>タバコの詳細表示</h1>

<%-- データベースから取得した商品情報の表示 --%>
<%
    String cigName = request.getParameter("cig_name"); // 銘柄
    String tar = request.getParameter("tar"); // タール
    String nicotine = request.getParameter("nicotine"); // ニコチン
    String price = request.getParameter("price"); // 価格
    String stock = request.getParameter("stock"); // 在庫
%>

<p>銘柄: <%= cigName %></p>
<p>タール: <%= tar %> mg</p>
<p>ニコチン: <%= nicotine %> mg</p>
<p>価格: <%= price %> 円</p>
<p>在庫: <%= stock %> 個</p>

<form action="purchaseForm.jsp" method="post">
    <input type="hidden" name="cig_name" value="<%= cigName %>">
    <input type="hidden" name="price" value="<%= price %>">
    <input type="hidden" name="stock" value="<%= stock %>">
    <input type="submit" value="購入">
</form>

<form action="productList.jsp" method="post">
    <input type="submit" value="TOP">
</form>

</body>
</html>
