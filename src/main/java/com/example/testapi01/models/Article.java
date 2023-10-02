package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name ="Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleID;
    @Column(name="ArticleName")
    private String articleName;
    @Column(name="createTime")
    private LocalDate createTime;
    @Column(name="AuthorName")
    private String authorName;
    @Column(name="ContentArticle")
    private String contentArticle;
    @Column(name="contentShort")
    private String contentShort;
    @Column(name="imageArticle")
    private byte[] imageArticle;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="topicID", foreignKey = @ForeignKey(name ="fk_Article_Topic"))
    private Topic topic;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="accountID", foreignKey = @ForeignKey(name="fk_Article_Account"))
    private Account account;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContentArticle() {
        return contentArticle;
    }

    public void setContentArticle(String contentArticle) {
        this.contentArticle = contentArticle;
    }

    public String getContentShort() {
        return contentShort;
    }

    public void setContentShort(String contentShort) {
        this.contentShort = contentShort;
    }

    public byte[] getImageArticle() {
        return imageArticle;
    }

    public void setImageArticle(byte[] imageArticle) {
        this.imageArticle = imageArticle;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
