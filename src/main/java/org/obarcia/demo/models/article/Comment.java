package org.obarcia.demo.models.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.obarcia.demo.models.user.User;

/**
 * Comment entity.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "comment")
public class Comment
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article", nullable = false)
    private Article article;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public Article getArticle()
    {
        return article;
    }
    public void setArticle(Article value)
    {
        article = value;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User value)
    {
        user = value;
    }
}
