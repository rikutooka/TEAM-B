package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.entity.ProductBean;

@WebServlet("/ProductDetail")
public class ProductDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductDetailServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // クエリパラメータから商品IDを取得
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                int itemId = Integer.parseInt(idStr);

                // DAOを使ってデータベースから商品情報を取得
                ProductDAO productDAO = new ProductDAO();
                ProductBean product = productDAO.getProductById(itemId);

                // 取得した商品情報をリクエストスコープに保存
                request.setAttribute("product", product);

                // 詳細ページにフォワード
                RequestDispatcher dispatcher = request.getRequestDispatcher("productDetail.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                // IDの形式が不正な場合のエラーハンドリング
                e.printStackTrace();
                response.sendRedirect("productList.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("productList.jsp");
            }
        } else {
            response.sendRedirect("productList.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 文字コード設定（フォームの値が文字化けしないようにする）
        request.setCharacterEncoding("UTF-8");

        // フォームから送られたデータを取得
        int id = Integer.parseInt(request.getParameter("id"));
        String cigName = request.getParameter("cig_name");
        int tar = Integer.parseInt(request.getParameter("tar"));
        double nicotine = Double.parseDouble(request.getParameter("nicotine"));
        int price = Integer.parseInt(request.getParameter("price"));
        String detail = request.getParameter("detail");
        int stock = Integer.parseInt(request.getParameter("stock"));

        // データベース更新処理を行う（DAOを使う）
        ProductDAO productDAO = new ProductDAO();
        boolean isUpdated = productDAO.updateProduct(id, cigName, tar, nicotine, price, detail, stock);

        // 更新が成功した場合、一覧ページへリダイレクト
        if (isUpdated) {
            response.sendRedirect("productListMaster.jsp");
        } else {
            request.setAttribute("errorMessage", "更新に失敗しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("productDetailMaster.jsp");
            dispatcher.forward(request, response);
        }
    }
}
