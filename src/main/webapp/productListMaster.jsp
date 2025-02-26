<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果一覧(管理者)</title>
</head>
<body>
	<%@ page import="java.util.List"%>
	<%@ page import="model.entity.ProductBean"%>
    
	<h2 style="text-align: center;">結果一覧</h2>

	<table border="1">
		<tr>
			<th>銘柄</th>
			<th>値段</th>
			<th>タール</th>
			<th>詳細</th>
			<th>削除</th>
		</tr>
		<%
		// リクエストスコープから productList を取得
		List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");

		// リストがnullでないかチェック
		if (productList != null && !productList.isEmpty()) {
			// ループして商品情報を表示
			for (ProductBean product : productList) {
		%>
		<tr>
			<td><%=product.getCigName()%></td>
			<td>¥<%=product.getPrice()%> (税込み)
			</td>
			<td><%=product.getTar()%>mg</td>
			<td>
				<form action="ProductDetail" method="GET">
					<input type="hidden" name="id" value="<%=product.getId()%>">
					<button type="submit">詳細</button>
				</form>
			</td>
			<td>
				<form action="ProductDelete" method="post">
					<input type="hidden" name="id" value="<%=product.getId()%>">
					<button type="submit">削除</button>
				</form>
			</td>
		</tr>
		<%
		}
		}
		%>
	</table>
	<form action="search.jsp" method="get">
		<button type="submit">TOP</button>
	</form>

</body>
</html>