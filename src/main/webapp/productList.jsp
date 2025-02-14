<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果一覧</title>
</head>
<body>
    <h2 style="text-align:center;">結果一覧</h2>
    
    <table border="1">
        <tr>
            <th>銘柄</th>
            <th>値段</th>
            <th>タール</th>
            <th>詳細</th>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.cigName}</td>
                <td>¥${product.price} (税込み)</td>
                <td>${product.tar}mg</td>
                <td><a href="productDetail.jsp?id=${product.id}">詳細</a></td>
            </tr>
        </c:forEach>
        </table>
        <form action="search.jsp" method="get">
            <button type="submit">TOP</button>
        </form>

</body>
</html>