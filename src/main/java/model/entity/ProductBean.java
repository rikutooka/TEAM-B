package model.entity;

import java.io.Serializable;

/**
 * m_product テーブルから抽出した情報を格納するクラス
 */
public class ProductBean implements Serializable {
    // ⓵ フィールド（DBから取得した商品情報を格納する）
    private int id;
    private String cigName;
    private int price;
    private double tar;

    // ⓶ publicな引数なし、処理なしのコンストラクタ
    public ProductBean() {
        // デフォルトコンストラクタ（処理なし）
    }

    // フィールドに値をセットするためのコンストラクタ
    public ProductBean(int id, String cigName, int price, double tar) {
        this.id = id;
        this.cigName = cigName;
        this.price = price;
        this.tar = tar;
    }

    // ⓷ アクセッサ（getter & setter）の定義
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCigName() {
        return cigName;
    }

    public void setCigName(String cigName) {
        this.cigName = cigName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTar() {
        return tar;
    }

    public void setTar(double tar) {
        this.tar = tar;
    }
}
