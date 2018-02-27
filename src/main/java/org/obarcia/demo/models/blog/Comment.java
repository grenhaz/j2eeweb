package org.obarcia.demo.models.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Post comment.
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
    @Column(name = "id_post")
    private int id_post;
    @Column(name = "id_user")
    private int id_user;
    @Column(name = "content")
    private String content;
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    public int getIdPost()
    {
        return id_post;
    }
    public void setIdPost(int value)
    {
        id_post = value;
    }
    public int getIdUser()
    {
        return id_user;
    }
    public void setIdUser(int value)
    {
        id_user = value;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
}
