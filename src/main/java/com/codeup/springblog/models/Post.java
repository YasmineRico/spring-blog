package com.codeup.springblog.models;
import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Column(nullable = false, length = 100)

    private String title;

    @Column(nullable = false)

    private String body;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;


    public Post() {

    }

    public Post(Long id, String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.id = id;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
