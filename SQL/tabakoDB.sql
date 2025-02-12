/* 既存のデータベースを削除 */
DROP DATABASE IF EXISTS cigarettesShop_db;
/* データベースの作成 */
CREATE DATABASE cigarettesShop_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
/* データベースの使用 */
USE cigarettesShop_db;
/* テーブルの作成 */
/* 従業員情報マスタ */
CREATE TABLE m_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20),
    password VARCHAR(16)
    );
/* データの挿入 */
INSERT INTO m_user(user_id,password) VALUES
    ('rikutooka','9017950506');
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
/*使用するデータベースを選択する*/