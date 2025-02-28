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
    	request.setCharacterEncoding("UTF-8");
        // 入力値取得
        String cig_name = request.getParameter("cig_name");
        Double tar = parseDouble(request.getParameter("tar"));
        Double nicotine = parseDouble(request.getParameter("nicotine"));
        Integer price = parseInteger(request.getParameter("price"));
        String category = request.getParameter("category");
        String flavor = request.getParameter("flavor");
        String detail = request.getParameter("detail");
        Integer stock = parseInteger(request.getParameter("stock"));
        
        System.out.println("取得したカテゴリー: " + category);
        System.out.println("取得したフレーバー: " + flavor);
        
        // 空文字をnullに変換
        //category = (category != null && !category.trim().isEmpty()) ? category : null;
        //flavor = (flavor != null && !flavor.trim().isEmpty()) ? flavor : null;

        // DAOを利用してDBに登録
        ProductDAO dao = new ProductDAO();
        try {

			dao.insertProduct(cig_name, tar, nicotine, price, stock, category, flavor, detail);
			// 成功したらリスト画面へリダイレクト
            response.sendRedirect("productListMaster.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			 // エラーメッセージを設定して登録画面に戻る
            request.setAttribute("errorMessage", "登録に失敗しました。もう一度試してください。");
            request.getRequestDispatcher("search.jsp").forward(request, response);
		}

    }

    // 小数のパース (エラーハンドリングを強化)
    private Double parseDouble(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;

        }
    }

    // 整数のパース (nullチェックを強化)
    private Integer parseInteger(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
            return null;
        }
    }
}
