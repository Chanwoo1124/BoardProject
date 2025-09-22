package board.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Post {
    private int id;
    private int authorId;
    private String title;
    private String content;
    private LocalDate createdDate;


    public Post(int id, int authorId, String title, String content, LocalDate createdDate){
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
