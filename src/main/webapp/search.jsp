<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.ProductDAO" %>
<%@ page import="java.util.List" %>

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
        ProductDAO productDAO = new ProductDAO();
        List<String> flavorList = productDAO.getAllFlavors();
    %>
    
    <form action="SearchServlet" method="get">
        <!--------------------- 銘柄 --------------------->
        <label>銘柄：</label>
        <input type="text" name="cig_name"><br>

        <!------------------- カテゴリー ----------------->
        <label>カテゴリー：</label>
        <select name="category">
             <option value="">選択してください</option>
            <option value="紙巻たばこ">紙巻たばこ</option>
        </select><br>

        <!-------------------- 価格帯 -------------------->
        <label>価格帯：</label>
        <input type="text" name="price_min"> ～ 
        <input type="text" name="price_max"><br>

        <!------------------- フレーバー ----------------->
        <label>フレーバー：</label>
        <select name="flavor">
            <option value="">選択してください</option>
            <% for(String flavor : flavorList) { %>
                <option value="<%= flavor %>"><%= flavor %></option>
            <% } %>
        </select><br>

        <input type="submit" value="検索">
    </form>

    <% if (isAdminLoggedIn) { %>
        <!-- 管理者ログイン後の場合、新規登録ボタンとログアウトボタンを表示 -->
        <a href="productRegister.jsp">
            <button type="button">新規登録</button>
        </a>

        <form action="LogoutServlet" method="post" style="display: inline;">
            <input type="submit" value="ログアウト">
        </form>
    <% } else { %>
        <!-- 管理者ログインしていない場合、ログインボタンを表示 -->
        <div class="right-bottom">
            <a href="login.jsp">
                <button type="button">管理者ログイン</button>
            </a>
        </div>
    <% } %>

</body>
</html>
