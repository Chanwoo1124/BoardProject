package board.dao;

import board.model.Post;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    private String url =
            "jdbc:mysql://127.0.0.1:3306/board?serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    private String uid = "root";
    private String password = "chan";
    private String driver = "com.mysql.cj.jdbc.Driver";


    public List<Post> findPage(int page)
            throws ClassNotFoundException, SQLException {

        int start = 1 + (page - 1) * 10;   //글의 순서 1, 11,21,31 등차수열

        final String sql =
                        "SELECT * " +
                        "FROM posts " +
                        "ORDER BY created_date DESC " +
                        "LIMIT ?, 10"; //

        Class.forName(driver);

        List<Post> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, uid, password);
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1,start);


            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int authorId = rs.getInt("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    LocalDate created = rs.getDate("created_date").toLocalDate();

                    list.add(new Post(id, authorId, title, content, created));
                }
            }
        }
        return list;
    }
}