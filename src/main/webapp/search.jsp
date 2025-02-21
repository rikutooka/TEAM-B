<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // セッション情報の出力
    System.out.println("Session adminUser: " + session.getAttribute("adminUser"));

    Boolean isAdminLoggedIn = false;
    if (session != null && session.getAttribute("adminUser") != null) {
        isAdminLoggedIn = true;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>タバコ検索画面</title>
</head>
<body>
    <h1>タバコ検索画面</h1>

    <form action="SearchServlet" method="get">
        <!--------------------- 銘柄 --------------------->
        <label>銘柄：</label>
        <input type="text" name="cig_name"><br>

        <!------------------- カテゴリー ----------------->
        <label>カテゴリー：</label>
        <select name="category">
            <option value="">選択してください</option>
            <option value="紙巻たばこ">紙巻たばこ</option>
            <option value=""></option>
        </select><br>

        <!-------------------- 価格帯 -------------------->
        <label>価格帯：</label>
        <input type="text" name="price_min"> ～ 
        <input type="text" name="price_max"><br>

        <!------------------- フレーバー ----------------->
        <label>フレーバー：</label>
        <select name="flavor">
            <option value="">選択してください</option>
            <option value="レギュラー">レギュラー</option>
            <option value="メンソール">メンソール</option>
            <option value="フルーツ">フルーツ</option>
        </select><br>

        <input type="submit" value="検索">

        <% if (isAdminLoggedIn) { %>
            <!-- 管理者ログイン後の場合、新規登録ボタンとログアウトボタンを表示 -->
            <a href="RegisterServlet">
                <button type="button">新規登録</button>
            </a>

            <form action="LogoutServlet" method="post" style="display: inline;">
                <button type="submit">ログアウト</button>
            </form>
        <% } else { %>
            <!-- 管理者ログインしていない場合、ログインボタンを表示 -->
            <div class="right-bottom">
                <a href="login.jsp">
                    <button type="button">管理者ログイン</button>
                </a>
            </div>
        <% } %>

    </form>
</body>
</html>
