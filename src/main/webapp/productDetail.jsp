<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.ProductBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコの詳細表示</title>
</head>
<body>
<h1>タバコの詳細表示</h1>

<%-- データベースから商品情報を取得 --%>
<%
   ProductBean product = (ProductBean) request.getAttribute("product");
   if (product != null) {
%>

<p>銘柄: <%= product.getCigName() %></p>
<p>タール: <%= product.getTar() %> mg</p>
<p>ニコチン: <%= product.getNicotine() %> mg</p>
<p>価格: <%= product.getPrice() %> 円</p>
<p>在庫: <%= product.getStock() %> 個</p>
<p>詳細: <%= product.getDetail() %></p>

<form action="purchaseForm.jsp" method="post">
    <input type="hidden" name="cig_name" value="<%= product.getCigName() %>">
    <input type="hidden" name="price" value="<%= product.getPrice() %>">
    <input type="hidden" name="stock" value="<%= product.getStock() %>">
    <input type="submit" value="購入">
</form>

<form action="productList.jsp" method="post">
    <input type="submit" value="TOP">
</form>
<%
    } else {
%>
        <p>商品情報が見つかりませんでした。</p>
<%
    }
%>

</body>
</html>
