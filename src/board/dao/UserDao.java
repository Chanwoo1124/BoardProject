package board.dao;

import board.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private String URL =
            "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true";
    private String USER = "root";
    private String PW   = "chan";


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
}