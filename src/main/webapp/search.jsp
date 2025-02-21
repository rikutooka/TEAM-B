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
    
    <%
        //ログイン状態のチェック
        HttpSession sessionObj = request.getSession(false);
        boolean isAdminLoggedIn = (sessionObj != null && sessionObj.getAttribute("adminUser") != null);
        String formAction = isAdminLoggedIn ? "productList.jsp" : "	productListMaster.jsp";
    %>
    
    <form action="SearchServlet" method="get">
    <!--------------------- 銘柄 --------------------->
        <label>銘柄：</label>
        <input type="text" name="cig_name"><br>
    <!------------------- カテゴリー  ---------------->
        <label>カテゴリー：</label>
        <select name="category">
            <option value="">すべて</option>
            <option value="レギュラー">レギュラー</option>
            <option value="menthol">メンソール</option>
        </select><br>
     <!-------------------- 価格帯  ----------------->
        <label>価格帯：</label>
        <input type="text" name="price_min"> ～
        <input type="text" name="price_max"><br>
     <!------------------- フレーバー ---------------->
        <label>フレーバー：</label>
        <select name="flavor">
            <option value="">選択してください</option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
            <option value=""></option>
        </select><br>
        
        
            <input type="submit" value="検索">
            
            <% if (isAdminLoggedIn) { %>
                <a href="RegisterServlet">
                    <button type="button">新規登録</button>
                </a>
            <% } %>
       
    </form>
    <br>
    <% if (!isAdminLoggedIn) { %>
        <div class="right-bottom">
            <a href="login.jsp">
                <button type="button">管理者ログイン</button>
            </a>
        </div>
    <% } %>
<%
    if (session != null && session.getAttribute("adminUser") != null) {
%>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="ログアウト">
    </form>
<%
    }
%>
</body>
</html>