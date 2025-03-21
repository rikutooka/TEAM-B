<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容の確認</title>
</head>
<body>
    <h2>入力内容の確認</h2>
    
    <table>
        <tr><td>商品：</td><td>${requestScope.cigName}</td></tr>
        <tr><td>個数：</td><td>${requestScope.quantity}</td></tr>
        <tr><td>合計金額：</td><td>¥${requestScope.totalPrice}</td></tr>
        <tr><td>氏名：</td><td>${requestScope.name}</td></tr>
        <tr><td>郵便番号：</td><td>${requestScope.postCode}</td></tr>
        <tr><td>住所：</td><td>${requestScope.address}</td></tr>
        <tr><td>電話番号：</td><td>${requestScope.telNumber}</td></tr>
        <tr><td>決済方法：</td><td>${requestScope.payMethod}</td></tr>
    </table>
    
    <form action="purchaseComplete" method="post">
        <input type="hidden" name="cigName" value="${requestScope.cigName}">
        <input type="hidden" name="quantity" value="${requestScope.quantity}">
        <input type="hidden" name="totalPrice" value="${requestScope.totalPrice}">
        <input type="hidden" name="name" value="${requestScope.name}">
        <input type="hidden" name="postCode" value="${requestScope.postCode}">
        <input type="hidden" name="address" value="${requestScope.address}">
        <input type="hidden" name="telNumber" value="${requestScope.telNumber}">
        <input type="hidden" name="payMethod" value="${requestScope.payMethod}">
        <input type="submit" value="購入">
    </form>

</body>
</html>