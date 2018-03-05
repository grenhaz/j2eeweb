package org.obarcia.demo.models.user;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    /**
     * Identificador.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * Email.
     */
    @NotEmpty
    @Email
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    /**
     * Contraseña.
     */
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Column(name = "password")
    private String password;
    /**
     * Nickname.
     */
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Column(name = "nickname")
    private String nickname;
    /**
     * Rol
     */
    @NotEmpty
    @Size(max = 16)
    @Column(name = "user_role")
    private String user_role;
    /**
     * Usuario activado o no.
     */
    @Column(name = "active")
    private Boolean active;
    /**
     * Fichero que hace de avatar.
     */
    @Size(max = 64)
    @Column(name = "avatar")
    private String avatar;
    /**
     * Clave para activación y cambio de contraseña.
     */
    @Size(max = 64)
    @Column(name = "ukey")
    private String ukey;
    /**
     * Fecha de creación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;
    /**
     * Listado de comentarios.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
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
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String value)
    {
        password = value;
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
    public String getUkey()
    {
        return ukey;
    }
    public void setUkey(String value)
    {
        ukey = value;
    }
    public Date getCreated()
    {
        return created;
    }
    public void setCreated(Date value)
    {
        created = value;
    }
    public Set<Comment> getComments()
    {
        return comments;
    }
}