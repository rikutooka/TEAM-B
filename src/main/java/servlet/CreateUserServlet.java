package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

@WebServlet("/CreateUser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エンコーディング設定
		request.setCharacterEncoding("UTF-8");
		//⓵リクエストパラメータを取得
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");

		// 入力チェック（空欄がある場合、エラーメッセージを表示して終了）
		if (userID == null || userID.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			request.setAttribute("message", "ユーザーIDとパスワードを入力してください。");
			RequestDispatcher rd = request.getRequestDispatcher("create-user.jsp");
			rd.forward(request, response);
			return; // ここで処理を終了
		}
		//テスト
		System.out.println("アカウント作成しました");
		try {
			UserDAO userDAO = new UserDAO();
			boolean result = userDAO.getUserInsertList(userID, password);
			// 成功した場合はsuccessメッセージをJSPに渡す
			if (result) {
				request.setAttribute("message", "アカウント作成しました！");
				request.setAttribute("userID", userID);
				request.setAttribute("password", password);
			} else {
				request.setAttribute("message", "アカウント作成に失敗しました。");
			}
			//create-user.jspへの遷移		
			RequestDispatcher rd = request.getRequestDispatcher("create-success.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("create-success.jsp");
			rd.forward(request, response);
		}

	}
}
