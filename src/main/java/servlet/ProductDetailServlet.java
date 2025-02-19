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
public class ProductDetailServlet  extends HttpServlet {
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
		 
	            RequestDispatcher dispatcher = request.getRequestDispatcher("productDetailMaster.jsp");
	            dispatcher.forward(request, response);
	 }
}
