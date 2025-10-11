package board.dao;

import board.model.Post;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    private String URL =  "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    private String USER = "root";
    private String PW   = "chan";

    // 드라이버가 계속 오류나서 챗지피티한테 코드 받아옴
    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (ClassNotFoundException ignore) {}
    }

    public List<Post> findPage(int page) throws SQLException {
        // 0이면 1번째부터  10이면 11번째부터
        int offset = (page - 1) * 10;


         String sql =
                "SELECT id, author_id, title, content, created_date " +
                        "FROM posts " +
                        "ORDER BY id DESC " +
                        "LIMIT ?, 10";
        // post인스턴스 담을 수 있는 list 생성
        List<Post> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id  = rs.getInt("id");
                    int authorId = rs.getInt("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Timestamp ts = rs.getTimestamp("created_date");
                    LocalDate created = (ts != null) ? ts.toLocalDateTime().toLocalDate() : null;
                    //값 가지고와서 리스트 추가
                    list.add(new Post(id, authorId, title, content, created));
                }
            }
        }
        return list;
    }

    // 게시판 숫자 받아와서 조회 후 게시판 상세 값 반환
    public Post detailPost(int id) throws SQLException {
         String sql =
                "SELECT id, author_id, title, content, created_date " +
                        "FROM posts WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int pid = rs.getInt("id");
                    int authorId = rs.getInt("author_id");
                    String title = rs.getString("title");
                    String content= rs.getString("content");
                    Timestamp ts = rs.getTimestamp("created_date");
                    LocalDate created = (ts != null) ? ts.toLocalDateTime().toLocalDate() : null;

                    return new Post(pid, authorId, title, content, created);
                }
            }
        }
        return null;
    }
    //게시판 등록 메소드
    public int insert(int authorId, String title, String content) throws SQLException {
        final String sql =
                "INSERT INTO posts (author_id, title, content, created_date) " +
                        "VALUES (?, ?, ?, NOW())";

        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, authorId);
            ps.setString(2, title);
            ps.setString(3, content);
            return ps.executeUpdate(); // 1이면 성공
        }
    }

    //게시판 업데이트 메소드
    public int update(int id, int authorId, String title, String content) throws SQLException {
         String sql = "UPDATE posts SET title=?, content=? WHERE id=? AND author_id=?";
        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, id);
            ps.setInt(4, authorId);
            return ps.executeUpdate();
        }
    }
    //게시판 삭제 메소드
    public int delete(int id, int authorId) throws SQLException {
        final String sql = "DELETE FROM posts WHERE id = ? AND author_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, authorId);
            return ps.executeUpdate();
        }
    }

}

