package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

/*
 * m_user
 */
public class UserDAO {
	/**
	 * m_userの全レコードからuser_idとpasswordカラムの値が格納されたListを返すメソッド
	 * @return user_idとpasswordの情報が入ったList
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<UserBean> getUserInfoList(String user_id,String password) throws ClassNotFoundException, SQLException{
		//戻り値用のList定義
		
		List<UserBean> userInfoList = new ArrayList<UserBean>();
		//⓵sql文の準備
		String sql = "SELECT user_id,password FROM m_user WHERE user_id = ? AND password = ?";
		//⓶DBとの接続およびsqlの実行準備
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);
			//⓷sql文の実行
			ResultSet res = pstmt.executeQuery();
			//⓸whileを用いたListへの格納操作
			while(res.next()){
				//⑥現在の選択行から各の値を取得し、各変数に格納
				//res.で呼び出すメソッドは取得したいデータ型によって変わる
				String userID = res.getString("user_id");
				String Password = res.getString("password");
				//⑦生成するBeanインスタンスのフィールドに⑥で作成した各変数の値をセット
				//今回はコンストラクタを用いてセット
				UserBean userBean = new UserBean(userID,Password);
				//⑧値をセットしたbeanインスタンスを、Listにセット
				userInfoList.add(userBean);
				
			}
		}
		return userInfoList;
	}
	
}
