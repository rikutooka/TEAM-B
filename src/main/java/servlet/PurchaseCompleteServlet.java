package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ConnectionManager;

@WebServlet("/purchaseComplete")
public class PurchaseCompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームのデータ取得（nullチェックを追加）
        String cigName = request.getParameter("cigName");
        if (cigName == null || cigName.isEmpty()) {
            // cigName が null または空の場合の処理
            throw new ServletException("商品名が不正です。");
        }

        int quantity = 0;
        double totalPrice = 0.0;

        // quantity の取得とパース
        String quantityStr = request.getParameter("quantity");
        if (quantityStr != null && !quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr); // 数値に変換
            } catch (NumberFormatException e) {
                throw new ServletException("個数の入力が不正です。");
            }
        } else {
            throw new ServletException("個数が不正です。");
        }

        // totalPrice の取得とパース
        String totalPriceStr = request.getParameter("totalPrice");
        if (totalPriceStr != null && !totalPriceStr.isEmpty()) {
            try {
                totalPrice = Double.parseDouble(totalPriceStr); // 数値に変換
            } catch (NumberFormatException e) {
                throw new ServletException("合計金額の入力が不正です。");
            }
        } else {
            throw new ServletException("合計金額が不正です。");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // DB接続（ConnectionManager を利用）
            conn = ConnectionManager.getConnection();

            // トランザクション開始
            conn.setAutoCommit(false);

            // 在庫を減らす処理
            String sql = "UPDATE m_item SET stock = stock - ? WHERE cig_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setString(2, cigName);
            int updatedRows = pstmt.executeUpdate();

            if (updatedRows > 0) {
                // 在庫更新
                conn.commit();
            } else {
                // 更新対象がなかった場合はロールバック
                conn.rollback();
                throw new SQLException("在庫更新に失敗しました。商品が見つかりませんでした。");
            }

        } catch (ClassNotFoundException | SQLException e) {
            // エラーが発生した場合のロールバック
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            throw new ServletException("データベース処理中にエラーが発生しました。");

        } finally {
            // リソース解放
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // リクエストスコープにデータをセット（購入完了画面で表示用）
        request.setAttribute("cigName", cigName);
        request.setAttribute("quantity", quantity);
        request.setAttribute("totalPrice", totalPrice);

        // 購入完了ページへフォワード
        request.getRequestDispatcher("purchaseComplete.jsp").forward(request, response);
    }
}
