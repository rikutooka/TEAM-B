<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコ検索画面</title>
</head>
<body>
    <h1>タバコ検索画面</h1>
    <form action="productList.jps" method="get">
    <!--------------------- 銘柄 --------------------->
        <label>銘柄：</label>
        <input type="text" name="cig_name"><br>
    <!------------------- カテゴリー  ---------------->
        <label>カテゴリー：</label>
        <select name="category">
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
        </select><br>
     <!-------------------- 価格帯  ----------------->
        <label>価格帯：</label>
        <input type="text" name="price_min"> ～
        <input type="text" name="price_max"><br>
     <!------------------- フレーバー ---------------->
        <label>フレーバー：</label>
        <select name="flavor">
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
        </select><br>
        
    </form>

</body>
</html>