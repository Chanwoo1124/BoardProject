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
