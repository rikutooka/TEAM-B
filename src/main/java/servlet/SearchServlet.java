package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ProductDAO;
import model.entity.ProductBean;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // フォームからのパラメータ取得
        String cigName = request.getParameter("cig_name");
        String category = request.getParameter("category");
        Integer priceMin = parseInteger(request.getParameter("price_min"));
        Integer priceMax = parseInteger(request.getParameter("price_max"));
        String flavor = request.getParameter("flavor");

        // DAOを利用して検索
        ProductDAO dao = new ProductDAO();
        List<ProductBean> productList = null;
        
        try {
            productList = dao.getProductList(cigName, category, priceMin, priceMax, flavor);
            
            if (productList == null) {
                productList = List.of(); // 空のリストを設定
            }
            
            System.out.println("検索結果件数: " + productList.size());
            
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
            throw new ServletException("Database error occurred",e);
        }
        
        System.out.println("取得した商品リスト: " + productList);

        // 検索結果をリクエストスコープに格納
        request.setAttribute("productList", productList);
        
        // セッションを取得し、管理者ログイン状態をチェック
        HttpSession session = request.getSession(false);
        boolean isAdminLoggedIn = (session != null && session.getAttribute("adminUser") != null);
        //追加消したとこ

        // 遷移先を決定
        String destination = isAdminLoggedIn ? "productListMaster.jsp" : "productList.jsp";

        // 一覧画面へ遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
    private Integer parseInteger(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : Integer.parseInt(str);
        } catch (NumberFormatException e) {
            
            return null;
        }
    }

}
