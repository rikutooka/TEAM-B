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

    public UserLoginServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // エンコーディング設定
        request.setCharacterEncoding("UTF-8");

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");

        // DAOのインスタンス生成
        UserDAO userDAO = new UserDAO();
        try {
            List<UserBean> userInfoList = userDAO.getUserInfoList(user_id, password);

            if (userInfoList.isEmpty()) {
                // ユーザー情報が取得できない場合、エラーメッセージをセット
                String errorMessage = "ユーザーIDまたはパスワードが間違っています。";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                // 認証成功時：セッションの取得（なければ新規作成）
                HttpSession session = request.getSession();
                // ユーザー情報リスト全体をセッションに保存
                session.setAttribute("adminUser", userInfoList);

                // 検索画面にリダイレクト
                response.sendRedirect("search.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            // エラーハンドリング
            e.printStackTrace();
            String errorMessage = "データベースに接続できませんでした。";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストディスパッチャーを使ってlogin.jspに遷移
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
