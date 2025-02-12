package model.entity;
/**
 * m_userテーブルから抽出した情報を格納クラス
 * @author 
 */
/**
 * Beanに必須 
 * ⓵Serializableインターフェースの実装
 * ⓶publicな引数なし、処理なしのコンストラクタを定義
 * ⓷プロパティのアクセッサを定義
 * 他は必須ではない
 */
//  
public class UserBean {
	//⓵フィールド(DBから抽出した情報が格納される)
	private String userID;
	private String password;
	
	//⓶publicな引数なし、処理なしコンストラクタの定義
	public UserBean() {
		//処理なし
	}
	//フィールドに値をセットするためのコンストラクタを定義
	public UserBean(String userID,String password) {
		//引数に渡された値をフィールドに代入(セット)
		this.userID = userID;
		this.password = password;
	}
	//⓷アクセッサを定義
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
