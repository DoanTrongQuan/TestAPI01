package com.example.testapi01.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicID;
    @Column(name="topicName")
    private String topicName;
    @Column(name="content")
    private String content;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="articleTypeID", foreignKey = @ForeignKey(name ="fk_Topic_articleType"))
    private ArticleType articletype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    @JsonBackReference
    private Set<Article> articleSett;

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleType getArticletype() {
        return articletype;
    }

    public void setArticletype(ArticleType articletype) {
        this.articletype = articletype;
    }

    public Set<Article> getArticleSett() {
        return articleSett;
    }

    public void setArticleSett(Set<Article> articleSett) {
        this.articleSett = articleSett;
    }
}
