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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 既存のセッションを取得（なければnull）
        if (session != null) {
        	System.out.println("ログアウト処理: セッション無効化前 - adminUser: " + session.getAttribute("adminUser"));
            session.invalidate(); // セッションを無効化（ログアウト）
            System.out.println("ログアウト処理: セッション無効化後");
        }else {
            System.out.println("ログアウト処理: 既にセッションなし");
        }
        
        response.sendRedirect("search.jsp"); // ログアウト後に search.jsp に戻る
    }
}
