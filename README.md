# 📝 Servlet & JSP 基盤 掲示板プロジェクト

## 🔗 🛠 技術スタック

- **Frontend**: JSP, HTML
- **Backend**: Java Servlet
- **Database**: MySQL
- **Data Access**: JDBC


---

## ❓ 作成背景 (Motivation)

**Webアプリケーションの全体的な構造と動作プロセスを知りたくて作成しました。**

Springを学ぶ前に、基礎となるServletとJSPを使用して、Webがどのように動くのかを直接実装して確認したいと思いました。

- **HTTPリクエスト/レスポンス**: クライアントとサーバーがデータをやり取りする過程を確認
- **DB連携**: JDBCを使用してデータベースに直接クエリを実行し、連携の仕組みを学習


## ERD

```mermaid
erDiagram
    USERS ||--o{ POSTS : "writes"

    USERS {
      INT id PK "AUTO_INCREMENT"
      VARCHAR login_id UK "NOT NULL"
      VARCHAR password_hash "NOT NULL"
      VARCHAR name "NOT NULL"
    }

    POSTS {
      INT id PK "AUTO_INCREMENT"
      INT author_id FK "NOT NULL"
      VARCHAR title "NOT NULL"
      TEXT content "NOT NULL"
      DATETIME created_date "DEFAULT CURRENT_TIMESTAMP"
    }
