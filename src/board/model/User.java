package board.model;

public class User {
    private int id;
    private String loginId;
    private String passwordHash;
    private String name;


    public User(String loginId, String passwordHash, String name) {
        this.loginId = loginId;
        this.passwordHash = passwordHash;
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public String getLoginId(){
        return loginId;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public String getName() {
        return name;
    }

}
