package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 既存のセッションを取得（存在する場合）
		HttpSession session = request.getSession(false);
		if (session != null) {
			// セッションを無効化
			session.invalidate();
		}
		// ログインページへリダイレクト
		response.sendRedirect("search.jsp");
	}
}
