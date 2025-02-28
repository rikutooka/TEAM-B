package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.ProductBean;

public class ProductDAO {
    /**
     * m_item の全レコードから銘柄・価格・タールを取得し、List に格納して返すメソッド
     * @return 商品情報が入った List
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public List<ProductBean> getProductList(String cigName, String category, Integer priceMin, Integer priceMax, String flavor) 
    		throws ClassNotFoundException, SQLException {
        // 戻り値用の List 定義
        List<ProductBean> productList = new ArrayList<>();

        // ① SQL文の準備
        String sql = "SELECT item_id, cig_name, price, tar, nicotine, stock, category, flavor, detail FROM m_item WHERE 1=1";
       

        
        if (cigName != null && !cigName.isEmpty()) {
            sql += " AND cig_name LIKE ?";
        }
        if (category != null && !category.isEmpty()) {
            sql += " AND category = ?";
        }
        if (priceMin != null) sql += " AND price >= ?";
        
        if (priceMax != null) sql += " AND price <= ?";
        
        if (flavor != null && !flavor.isEmpty()) {
            sql += " AND flavor LIKE ?";
        }

        // ② DBとの接続および SQLの実行準備
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
        	
        	// ④ プレースホルダの値をセット
            int paramIndex = 1;
            if (cigName != null && !cigName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + cigName + "%");
            }
            if (category != null && !category.isEmpty()) {
                pstmt.setString(paramIndex++, category);
            }
            if (priceMin != null) pstmt.setInt(paramIndex++, priceMin);
            
            if (priceMax != null) pstmt.setInt(paramIndex++, priceMax);
            
            if (flavor != null && !flavor.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + flavor + "%");
            }

            // ③ SQL文の実行
            ResultSet res = pstmt.executeQuery();

            // ④ whileを用いた List への格納操作
            while (res.next()) {
                // ⑤ 取得した各カラムの値を変数に格納
                int id = res.getInt("item_id");
                String name = res.getString("cig_name");
                int price = res.getInt("price");
                int tar = res.getInt("tar");
                double nicotine = res.getDouble("nicotine");
                int stock = res.getInt("stock");
                String Category = res.getString("category");
                String Flavor = res.getString("flavor");
                String detail = res.getString("detail");
                

                // ⑥ `ProductBean` インスタンスを生成し、値をセット
                ProductBean product = new ProductBean(id, name, price, tar, nicotine, stock, Category, Flavor, detail);

                // ⑦ List に `Product` インスタンスを追加
                productList.add(product);
            }
        }

        // ⑧ 結果を返す
        return productList;
    }
    public ProductBean getProductById(int id) throws ClassNotFoundException, SQLException {
    	String sql = "SELECT item_id, cig_name, price, tar, nicotine, stock, category, flavor, detail FROM m_item WHERE item_id = ?";
    	try (Connection con = ConnectionManager.getConnection();
    	         PreparedStatement pstmt = con.prepareStatement(sql)) {
    		 pstmt.setInt(1, id);
    	        ResultSet res = pstmt.executeQuery();
    	        if (res.next()) {
    	            return new ProductBean(
    	                res.getInt("item_id"),
    	                res.getString("cig_name"),
    	                res.getInt("price"),
    	                res.getInt("tar"),
    	                res.getDouble("nicotine"),
    	                res.getInt("stock"),
    	                res.getString("category"),
    	                res.getString("flavor"),
    	                res.getString("detail")
    	            );
    	        }
    	    }
    	        
    	
    	return null;
    }
        public boolean updateProduct(int id, String cigName, int tar, double nicotine, int price, String detail, int stock) {
            String sql = "UPDATE m_item SET cig_name=?, tar=?, nicotine=?, price=?, detail=?, stock=? WHERE item_id=?";

            try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, cigName);
                pstmt.setInt(2, tar);
                pstmt.setDouble(3, nicotine);
                pstmt.setInt(4, price);
                pstmt.setString(5, detail);
                pstmt.setInt(6, stock);
                pstmt.setInt(7, id);

                int result = pstmt.executeUpdate();
                return result > 0; // 1以上なら成功
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    
    
    public void insertProduct(String cig_name, Double tar, Double nicotine, Integer price, Integer stock, String category, String flavor, String detail) throws SQLException, ClassNotFoundException {
        // SQL文の準備

        String sql = "INSERT INTO m_item (cig_name, tar, nicotine, price, stock, category, flavor, detail) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        // DB接続とSQLの実行準備
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // プレースホルダに値をセット
            pstmt.setString(1, cig_name);
            pstmt.setDouble(2, tar);
            pstmt.setDouble(3, nicotine);
            pstmt.setInt(4, price);  
            pstmt.setInt(5, stock);
            pstmt.setString(6, category);
            pstmt.setString(7, flavor);
            pstmt.setString(8, detail);
            

            System.out.println("DAO に渡されたカテゴリー: " + category);
            System.out.println("DAO に渡されたフレーバー: " + flavor);

            // SQL文を実行
            pstmt.executeUpdate();
        }
    }
    
    /**
     * 指定されたIDの商品を削除するメソッド
     * @param id 削除する商品のID
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteProduct(int id) throws SQLException, ClassNotFoundException {
        // SQL文の準備
        String sql = "DELETE FROM m_item WHERE item_id = ?";

        // DB接続とSQLの実行
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // プレースホルダにIDをセット
        	pstmt.setInt(1, id);

            // SQLを実行
            pstmt.executeUpdate();
        }
    }
    /**
     * フレーバー一覧を取得するメソッド
     * @return List<String> フレーバー名のリスト
     */
    public List<String> getAllFlavors() {
    	 List<String> flavors = new ArrayList<>();
    	 String sql = "SELECT flavor FROM m_item";
    	 try (Connection con = ConnectionManager.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet res = pstmt.executeQuery()) {
    		 while (res.next()) {
                 flavors.add(res.getString("flavor"));
             }
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         
    	 }
    	return flavors;
    	
    }
}