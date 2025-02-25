package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ProductDAO;
import model.entity.ProductBean;

@WebServlet("/ProductDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// クエリパラメータから商品IDを取得
		String idStr = request.getParameter("id");

		int itemId = Integer.parseInt(idStr);

		// DAOを使ってデータベースから商品情報を取得
		ProductDAO productDAO = new ProductDAO();
		ProductBean product = null;

		try {
			product = productDAO.getProductById(itemId); // 商品IDで検索
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取得した商品情報をリクエストスコープに保存
		request.setAttribute("product", product);
		// セッションを取得し、ログイン状態を確認
        HttpSession session = request.getSession(false);
        boolean isAdminLoggedIn = (session != null && session.getAttribute("adminUser") != null);

        // ログイン状態に応じて遷移先を決定
        String destination = isAdminLoggedIn ? "productDetailMaster.jsp" : "productDetail.jsp";
        // 遷移先のページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字コード設定（フォームの値が文字化けしないようにする）
		request.setCharacterEncoding("UTF-8");
		// フォームから送られたデータを取得
		// 商品ID（hidden）
		int id = Integer.parseInt(request.getParameter("id"));
		// 銘柄
		String cigName = request.getParameter("cig_name");
		// タール
		int tar = Integer.parseInt(request.getParameter("tar"));
		// ニコチン
		double nicotine = Double.parseDouble(request.getParameter("nicotine"));
		// 価格
		int price = Integer.parseInt(request.getParameter("price"));
		// 商品の説明
		String detail = request.getParameter("detail");
		// 在庫
		int stock = Integer.parseInt(request.getParameter("stock"));
		 // ここでデータベース更新処理を行う（DAOを使う）
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
