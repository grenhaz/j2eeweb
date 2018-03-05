package org.obarcia.demo.models.user;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.demo.constraints.NicknameConstraint;

/**
 * Formulario de perfil del usuario.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
public class ProfileForm
{
    /**
     * Identificador del usuario.
     */
    private Integer id;
    /**
     * Nickname.
     */
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    private String nickname;
    /**
     * Fichero que hace de avatar.
     */
    @Size(max = 64)
    @Column(name = "avatar")
    private String avatar;
    
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
    public String getNickname()
    {
        return nickname;
    }
    public void setNickname(String value)
    {
        nickname = value;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public void setAvatar(String value)
    {
        avatar = value;
    }
}