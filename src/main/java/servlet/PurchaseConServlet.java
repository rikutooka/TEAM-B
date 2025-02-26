package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/purchaseCon")
public class PurchaseConServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
        // フォームからデータを取得
        String cigName = request.getParameter("cig_Name");
        Integer quantity = parseInteger(request.getParameter("quantity"));
        Double totalPrice = parseDouble(request.getParameter("totalPrice"));
        String name = request.getParameter("name");
        String postCode = request.getParameter("post_code");
        String address = request.getParameter("address");
        String telNumber = request.getParameter("tel_number");
        String payMethod = request.getParameter("pay_method");

        // 取得したデータをリクエストスコープに格納
        request.setAttribute("cigName", cigName);
        request.setAttribute("quantity", quantity);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("name", name);
        request.setAttribute("postCode", postCode);
        request.setAttribute("address", address);
        request.setAttribute("telNumber", telNumber);
        request.setAttribute("payMethod", payMethod);

        // 確認画面へ遷移
        request.getRequestDispatcher("purchaseCon.jsp").forward(request, response);
    }
    private Integer parseInteger(String str) {
        try {
            return (str == null || str.isEmpty()) ? 0 : Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException (Integer): " + e.getMessage());
            return 0;
        }
    }

    private Double parseDouble(String str) {
        try {
            return (str == null || str.isEmpty()) ? 0.0 : Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException (Double): " + e.getMessage());
            return 0.0;
        }
    }

}

