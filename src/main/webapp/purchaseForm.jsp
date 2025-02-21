<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入者情報入力</title>
<script>
function updateTotalPrice() {
    let price = document.getElementById("price").value;
    let quantity = document.getElementById("quantity").value;
    let totalPrice = price * quantity;
    document.getElementById("totalPrice").value = totalPrice;
}
</script>
</head>
<body>
    <h2>購入者情報入力</h2>
    
    <%
        request.setCharacterEncoding("UTF-8");
        String cigName = request.getParameter("cig_name");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
    %>
    
    <form action="purchaseCon" method="post">
        <table>
            <tr>
                <td>商品：</td>
                <td><input type="text" name="cig_Name" value="<%= cigName %>" readonly></td>
            </tr>
            <tr>
                <td>個数：</td>
                <td><input type="number" id="quantity" name="quantity" min="1" max="<%= stock %>" required oninput="updateTotalPrice()"></td>
            </tr>
            <tr>
                <td>単価：</td>
                <td><input type="number" id="price" value="<%= price %>" readonly></td>
            </tr>
            <tr>
                <td>合計金額：</td>
                <td><input type="number" id="totalPrice" name="totalPrice" readonly></td>
            </tr>
            <tr>
                <td>氏名：</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>郵便番号：</td>
                <td><input type="text" name="post_code" required></td>
            </tr>
            <tr>
                <td>住所：</td>
                <td><input type="text" name="address" required></td>
            </tr>
            <tr>
                <td>電話番号：</td>
                <td><input type="text" name="tel_number" required></td>
            </tr>
            <tr>
                <td>決済方法：</td>
                <td>
                    <select name="pay_method" required>
                        <option value="クレジットカード">クレジットカード</option>
                        <option value="銀行振込">銀行振込</option>
                        <option value="コンビニ決済">コンビニ決済</option>
                    </select>
                </td>
            </tr>
        </table>
        
        <button type="submit">確認</button>
    </form>

</body>
</html>