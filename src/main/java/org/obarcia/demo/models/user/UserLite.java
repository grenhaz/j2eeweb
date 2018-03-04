package org.obarcia.demo.models.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "usuario")
public class UserLite
{
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "user_role")
    private String user_role;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created")
    private Date created;
    
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
    }
    public String getUserRole()
    {
        return user_role;
    }
    public void setUserRole(String value)
    {
        user_role = value;
    }
    public Boolean getActive()
    {
        return active;
    }
    public void setActive(Boolean value)
    {
        active = value;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
    public Date getCreated()
    {
        return created;
    }
    public void setCreated(Date value)
    {
        created = value;
    }
    /**
     * Devuelve la fecha de alta formateada.
     * @return Fecha de alta formateada.
     */
    public String getFormattedCreated()
    {
        if (created != null) {
            return format.format(created);
        }
        
        return "";
    }
}
