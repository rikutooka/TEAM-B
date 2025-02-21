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
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 
		 response.setContentType("text/html; charset=UTF-8");
		 response.setCharacterEncoding("UTF-8");

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
	}