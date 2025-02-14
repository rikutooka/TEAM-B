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
     * m_product の全レコードから銘柄・価格・タールを取得し、List に格納して返すメソッド
     * @return 商品情報が入った List
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public List<ProductBean> getProductList(String cigName, String category, Integer priceMin, Integer priceMax, String flavor) 
    		throws ClassNotFoundException, SQLException {
        // 戻り値用の List 定義
        List<ProductBean> productList = new ArrayList<>();

        // ① SQL文の準備
        String sql = "SELECT id, cig_name, price, tar FROM m_product WHERE 1=1";
        
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
                int id = res.getInt("id");
                String name = res.getString("cig_name");
                int price = res.getInt("price");
                double tar = res.getDouble("tar");

                // ⑥ `ProductBean` インスタンスを生成し、値をセット
                ProductBean product = new ProductBean(id, name, price, tar);

                // ⑦ List に `Product` インスタンスを追加
                productList.add(product);
            }
        }

        // ⑧ 結果を返す
        return productList;
    }
    
    public void insertProduct(String cig_name, Double tar, Double nicotine, Integer price, String detail, Integer stock) throws SQLException, ClassNotFoundException {
        // SQL文の準備
        String sql = "INSERT INTO m_product (cig_name, tar, nicotine, price, detail, stock) VALUES (?, ?, ?, ?, ?, ?)";

        // DB接続とSQLの実行準備
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // プレースホルダに値をセット
            pstmt.setString(1, cig_name);
            pstmt.setDouble(2, tar);
            pstmt.setDouble(3, nicotine);
            pstmt.setInt(4, price);  
            pstmt.setString(5, detail);
            pstmt.setInt(6, stock);

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
        String sql = "DELETE FROM m_product WHERE id = ?";

        // DB接続とSQLの実行
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // プレースホルダにIDをセット
            pstmt.setInt(1, id);

            // SQLを実行
            pstmt.executeUpdate();
        }
    }

}