package org.obarcia.demo.models.user;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.obarcia.demo.models.article.Comment;

/**
 * User.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "usuario")
public class User
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private String user_role;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String value)
    {
        username = value;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String value)
    {
        password = value;
    }
    public String getUserRole()
    {
        return user_role;
    }
    public void setUserRole(String value)
    {
        user_role = value;
    }
    public Set<Comment> getComments()
    {
        return comments;
    }
}
