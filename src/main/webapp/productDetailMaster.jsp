<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タバコの詳細表示(管理者)</title>
</head>
<body>
    <h2>詳細</h2>
    <form action="ProductDeleteServlet" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        
        <table>
           <td>銘柄：</td>
                <td><%= cig_name %></td>
                <td><input type="text" name="cig_name" value="<%= cig_name %>"></td>
            </tr>
            <tr>
                <td>タール：</td>
                <td><%= tar %>mg</td>
                <td><input type="text" name="tar" value="<%= tar %>"></td>
            </tr>
            <tr>
                <td>ニコチン：</td>
                <td><%= nicotine %>mg</td>
                <td><input type="text" name="nicotine" value="<%= nicotine %>"></td>
            </tr>
            <tr>
                <td>価格：</td>
                <td>¥<%= price %>（税込み）</td>
                <td><input type="text" name="price" value="<%= price %>"></td>
            </tr>
            <tr>
                <td>詳細：</td>
                <td><%= detail %></td>
                <td><input type="text" name="detail" value="<%= detail %>"></td>
            </tr>
            <tr>
                <td>在庫：</td>
                <td><%= stock %>個</td>
                <td><input type="text" name="stock" value="<%= stock %>"></td>
            </tr>
           
        </table>
        
        <button type="submit">更新</button>
        
    </form>
    
    <form action="productListMaster.jsp">
        <button type="submit">TOP</button>
    </form>

</body>
</html>