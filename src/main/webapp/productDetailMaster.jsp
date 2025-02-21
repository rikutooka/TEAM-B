<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコの詳細表示(管理者)</title>
</head>
<body>
	<%@ page import="java.util.List"%>
	<%@ page import="model.entity.ProductBean"%>

	<h2>詳細</h2>
	<%
	// リクエストスコープから productList を取得
	ProductBean product = (ProductBean) request.getAttribute("product");

	// リストがnullでないかチェック
	if (product != null) {
	%>
	<form action="ProductDetail" method="post">
		<input type="hidden" name="id" value="<%=product.getId()%>">


		<p>銘柄：</p>
		<p><%=product.getCigName()%>
			<input type="text" name="cig_name">
		</p>

		<p>タール：</p>
		<p><%=product.getTar()%>mg <input type="text" name="tar">
		</p>

		<p>ニコチン：</p>
		<p><%=product.getNicotine()%>mg <input type="text" name="nicotine">
		</p>

		<p>価格：</p>
		<p>
			¥<%=product.getPrice()%>（税込み) <input type="text" name="price">
		</p>

		<p>詳細:</p>
		<p><%=product.getDetail()%>
			<input type="text" name="detail">
		</p>

		<p>在庫:</p>
		<p><%=product.getStock()%>個 <input type="text" name="stock">
		</p>
		<button type="submit">更新</button>
	</form>
	<%
	} else {
	%>
	<p>商品が見つかりませんでした。</p>
	<%
	}
	%>
	<form action="search.jsp">
		<button type="submit">TOP</button>
	</form>

</body>
</html>