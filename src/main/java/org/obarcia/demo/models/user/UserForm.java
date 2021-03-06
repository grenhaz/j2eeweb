package org.obarcia.demo.models.user;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.demo.constraints.EmailUniqueConstraint;
import org.obarcia.demo.constraints.NicknameConstraint;

/**
 * Formulario del usuario.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
@EmailUniqueConstraint(field = "email", message = "{error.email.unique}")
public class UserForm
{
    /**
     * Identificador.
     */
    private Integer id;
    /**
     * Email.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Email
    @Size(max = 128)
    private String email;
    /**
     * Nickname.
     */
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.NicknamePattern}")
    private String nickname;
    
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
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
    }
}