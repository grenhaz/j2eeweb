package org.obarcia.demo.models.user;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Formulario del usuario.
 * 
 * @author obarcia
 */
public class UserForm
{
    /**
     * Email.
     */
    @NotEmpty
    @Email
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    /**
     * Nickname.
     */
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @Column(name = "nickname")
    private String nickname;
    
    // ******************************************
    // GETTER & SETTER
    // ******************************************
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