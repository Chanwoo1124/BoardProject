package board.dao;

import board.model.Post;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private String URL =
            "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    private String USER = "root";
    private String PW = "chan";


    public List<Post> findPage(int page) throws SQLException {
        //값을 페이지로 받아서 목록 검색
        int offset = (page - 1) * 10;
        //최신 글 기준으로 10개씩 출력 (OFFSET은 0 이면 1부터)
        String sql =
                "SELECT id, author_id, title, content, created_date " +
                        "FROM posts " +
                        "ORDER BY id DESC " +
                        "LIMIT 10 OFFSET ?";

        List<Post> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int authorId = rs.getInt("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date date = rs.getDate("created_date");


                    list.add(new Post(id, authorId, title, content, date.toLocalDate()));
                }
            }
        }
        return list;
    }
    public Post findById(int id) throws SQLException {
        String sql = "SELECT id, author_id, title, content, created_date FROM posts WHERE id = ?";
        try (java.sql.Connection con = DriverManager.getConnection(URL, USER, PW);
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.sql.Timestamp ts = rs.getTimestamp("created_date");
                    java.time.LocalDate created = (ts != null) ? ts.toLocalDateTime().toLocalDate() : null;
                    return new Post(
                            rs.getInt("id"),
                            rs.getInt("author_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            created
                    );
                }
            }
        }
        return null;
    }


}

