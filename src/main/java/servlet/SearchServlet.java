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
        List<ProductBean> productList;
        
        try {
            productList = dao.getProductList(cigName, category, priceMin, priceMax, flavor);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database error occurred",e);
        }

        // 検索結果をリクエストスコープに格納
        request.setAttribute("productList", productList);

        // 一覧画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("productListMaster.jsp");
        dispatcher.forward(request, response);
    }
    private Integer parseInteger(String str) {
        try {
            return (str == null || str.isEmpty()) ? null : Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
            return null;
        }
    }

}
