package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 入力値取得
        String cig_name = request.getParameter("cig_name");
        Double tar = parseDouble(request.getParameter("tar"));
        Double nicotine = parseDouble(request.getParameter("nicotine"));
        Integer price = parseInteger(request.getParameter("price"));
        String detail = request.getParameter("detail");
        Integer stock = parseInteger(request.getParameter("stock"));

        // DAOを利用してDBに登録
        ProductDAO dao = new ProductDAO();
        try {
			dao.insertProduct(cig_name, tar, nicotine, price, detail, stock);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// エラーメッセージ
			request.setAttribute("errorMessage", "登録に失敗しました。もう一度試してください。");
			
        // 一覧画面にリダイレクト
        response.sendRedirect("productListMaster.jsp");
		}
    }
        private Double parseDouble(String str) {
            try {
                return (str == null || str.isEmpty()) ? null : Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        private Integer parseInteger(String str) {
            try {
                return (str.isEmpty()) ? null : Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException: " + e.getMessage());
                return null;
            }
        }

}
