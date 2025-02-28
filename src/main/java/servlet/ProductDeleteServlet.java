package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;

@WebServlet("/ProductDelete")
public class ProductDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                ProductDAO dao = new ProductDAO();
                dao.deleteProduct(id);

                // 削除後の検索条件を取得
                String cigName = request.getParameter("cig_name");
                String category = request.getParameter("category");
                String priceMin = request.getParameter("price_min");
                String priceMax = request.getParameter("price_max");
                String flavor = request.getParameter("flavor");

                // 検索条件を `SearchServlet` に引き継ぐためのリダイレクトURLを作成
                String redirectUrl = String.format(
                    "SearchServlet?cig_name=%s&category=%s&price_min=%s&price_max=%s&flavor=%s",
                    encodeParam(cigName), encodeParam(category), encodeParam(priceMin), encodeParam(priceMax), encodeParam(flavor)
                );

                response.sendRedirect(redirectUrl);
                return; // sendRedirectの後に処理を続けないようにする
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
               
                return;
            }
        }

        // IDが空だった場合、一覧ページへ
        response.sendRedirect("productListMaster.jsp");
    }

    // URLエンコードを行うメソッド
    private String encodeParam(String param) {
        return (param == null || param.isEmpty()) ? "" : URLEncoder.encode(param, StandardCharsets.UTF_8);
    }
}