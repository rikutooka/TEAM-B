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
        <tr><td>商品：</td><td>${sessionScope.cig_name}</td></tr>
        <tr><td>個数：</td><td>${sessionScope.quantity}</td></tr>
        <tr><td>合計金額：</td><td>¥${sessionScope.totalPrice}</td></tr>
        <tr><td>氏名：</td><td>${sessionScope.name}</td></tr>
        <tr><td>郵便番号：</td><td>${sessionScope.post_code}</td></tr>
        <tr><td>住所：</td><td>${sessionScope.address}</td></tr>
        <tr><td>電話番号：</td><td>${sessionScope.tel_number}</td></tr>
        <tr><td>決済方法：</td><td>${sessionScope.pay_method}</td></tr>
    </table>

</body>
</html>