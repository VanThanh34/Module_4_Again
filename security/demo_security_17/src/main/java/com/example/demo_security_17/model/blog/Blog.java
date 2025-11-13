package com.example.demo_security_17.model.blog;





import com.example.demo_security_17.model.AppUser;
import jakarta.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String img;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "User_Id")
    private AppUser appUser;

    public Blog() {
    }

    public Blog(int id, String title, String img, String content, AppUser appUser) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.content = content;
        this.appUser = appUser;
    }

    public Blog(String title, String content,String img) {
        this.title = title;
        this.content = content;
        this.img =img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
