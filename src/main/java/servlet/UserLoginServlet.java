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

import model.dao.UserDAO;
import model.entity.UserBean;

@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		//エンコーディング設定
				request.setCharacterEncoding("UTF-8");
				
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		//①DAOのインスタンス生成
		UserDAO userDAO = new UserDAO();
		try {
			 List<UserBean> userInfoList = userDAO.getUserInfoList(user_id, password);
			 
			 if(userInfoList.isEmpty()) {
				// ユーザー情報が取得できない場合、エラーメッセージをセット
				 String errorMessage = "ユーザーIDまたはパスワードが間違っています。";
				 request.setAttribute("errorMessage", errorMessage);
				 RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
			 }else {
	                // ユーザー情報が取得できた場合、リクエストにユーザー情報をセット
				    HttpSession session = request.getSession(); 
				    session.setAttribute("adminUser", userInfoList.get(0));
				    

	                // search.jspに遷移
	                //RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
	                //rd.forward(request, response);
				    
				    //検索画面にリダイレクト
				    response.sendRedirect("search.jsp");
	            }
		}
		catch (ClassNotFoundException | SQLException e) {
			 // エラーハンドリング
            e.printStackTrace();
            String errorMessage = "データベースに接続できませんでした。";
			 request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
		}
				
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
//		//テスト
//		System.out.println("アカウント作成");
//		//create-user.jspへの遷移		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}
