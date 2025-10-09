package board.dao;

import board.model.User;

import java.sql.*;

public class UserDao {
    private String URL =
            "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    private String USER = "root";
    private String PW = "chan";


    public int userInsert(User u) throws SQLException {
        String sql = "INSERT INTO users (login_id, password_hash, name) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getLoginId());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getName());


            // 성공시 1 , 실패시 0
            return ps.executeUpdate();
        }
    }

    // 아이디 비번 받아서 조회 후 맞으면 1 값 반환 틀리면 0 반환
    public int userFind(String loginId, String pwd) throws SQLException {
        String sql = "SELECT id, password_hash FROM users WHERE login_id = ? AND password_hash = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, loginId);
            ps.setString(2, pwd);
            try (ResultSet rs = ps.executeQuery()) {
                // 조회 후 같으면 id 반환
                if (rs.next()) {
                    int i = rs.getInt("id");
                    return i;
                } else {
                    return 0;
                }


            }
        }


    }
}