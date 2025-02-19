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
	    private int tar;
	    private double nicotine;
	    private int stock;
	    private String category;
	    private String flavor;
	    private String detail;

	    // ⓶ publicな引数なし、処理なしのコンストラクタ
	    public ProductBean() {
	        // デフォルトコンストラクタ（処理なし）
	    }

	    // フィールドに値をセットするためのコンストラクタ
	    public ProductBean(int id, String cigName, int price, int tar, 
	    		double nicotine,int stock, String category, String flavor, String detail) {
	        this.id = id;
	        this.cigName = cigName;
	        this.price = price;
	        this.tar = tar;
	        this.nicotine = nicotine;
	        this.stock = stock;
	        this.category = category;
	        this.flavor = flavor;
	        this.detail = detail;
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

		public int getTar() {
			return tar;
		}

		public void setTar(int tar) {
			this.tar = tar;
		}

		public double getNicotine() {
			return nicotine;
		}

		public void setNicotine(double nicotine) {
			this.nicotine = nicotine;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getFlavor() {
			return flavor;
		}

		public void setFlavor(String flavor) {
			this.flavor = flavor;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

	}