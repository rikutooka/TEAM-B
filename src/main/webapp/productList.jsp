<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.ProductBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果一覧</title>
</head>
<body>
    <%@ page import="java.util.List"%>
	<%@ page import="model.entity.ProductBean"%>
    <h2 style="text-align:center;">結果一覧</h2>
    
    <table border="1">
        <tr>
            <th>銘柄</th>
            <th>値段</th>
            <th>タール</th>
            <th>詳細</th>
        </tr>
        
        <%  //リクエストスコープから productList を取得
            List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (ProductBean product : productList) {
        %>
            <tr>
                <td><%= product.getCigName() %></td>
                <td>¥<%= product.getPrice() %>(税込み)</td>
                <td><%= product.getTar() %>mg</td>
                <td><a href="ProductDetail?id=<%= product.getId() %>">詳細</a></td>
            </tr>
        <% 
                }
            } else { 
        %>
            <!-- 検索結果が空の場合のメッセージ -->
            <p style="text-align:center; color:red;">該当する商品が見つかりませんでした。</p>
        <% 
            }
        %>
        
        </table>
        
        <form action="search.jsp" method="get">
            <button type="submit">TOP</button>
        </form>

</body>
</html>