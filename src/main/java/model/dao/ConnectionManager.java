package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/**
	 * データベースURL
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/cigarettesShop_db";

	/**
	 * ユーザ名
	 * データベースにアクセスするユーザ
	 */
	private static final String USER = "cigarMaster";

	/**
	 * パスワード
	 * データベースにアクセスするユーザのパスワード
	 */
	private static final String PASSWORD = "root";

	/**
	 * データベース接続確立しコネクションを返す
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// ドライバの読み込み
		Class.forName("com.mysql.jdbc.Driver");
		// データベース接続確立
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
