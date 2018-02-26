package org.obarcia.demo.models.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Blog Post.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
}
