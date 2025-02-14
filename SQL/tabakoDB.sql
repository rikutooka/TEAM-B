/*ユーザーを作成する*/
CREATE USER 'cigarMaster'@'localhost' IDENTIFIED BY 'root'
/*ユーザーの権限を確認*/
SHOW GRANTS FOR 'cigarMaster'@'localhost';
/*ユーザーに権限を付与*/
GRANT ALL ON cigarettesShop_db TO cigarMaster;
/*作成したユーザーで接続*/
exit
mysql -u cigarMaster -p
root
/*ユーザーが切り替わっていることを確認*/
SELECT cigarMaster(), current_user();
/*---------------------------------------------------------*/
/* 既存のデータベースを削除 */
DROP DATABASE IF EXISTS cigarettesShop_db;
/* データベースの作成 */
CREATE DATABASE cigarettesShop_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
/* データベースの使用 */
USE cigarettesShop_db;
/* テーブルの作成 */
CREATE TABLE m_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    password VARCHAR(16)
    );
/* データの挿入 */
INSERT INTO m_user(user_id,password) VALUES
    ('rikutooka','9017950506');
/*---------------------------------------------------------*/
/* テーブルの作成 */
CREATE TABLE m_item (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    cig_name VARCHAR(30),
    tar INT,
    nicotine DOUBLE,
    price INT,
    stock INT,
    category VARCHAR(15),
    flavor VARCHAR(15),
    detail VARCHAR(200)
    );
/* データの挿入 */
INSERT INTO m_item(cig_name,tar,nicotine,price,stock,category,flavor,detail) VALUES
    ('ハイライト','17','1.4','520','50','レギュラー','','国内最初のフィルター製品。洋酒（ラム）タイプの香料を用いた、香り豊かでうまみのある製品');
