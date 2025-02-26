<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了</title>
<style>
    body {
    text-align: center;
    }
    
</style>
</head>
<body>
    <h1>購入完了</h1>
    <p>ご購入ありがとうございました。</p>
    
    <p>商品名：${requestScope.cigName}</p> 
    <p>個数：${requestScope.quantity} 個</p>
    <p>合計金額：¥${requestScope.totalPrice}</p>
    
    <form action="search.jsp" method="get">
        <button type="submit">トップページへ戻る</button>
    </form>

</body>
</html>