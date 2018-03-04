package org.obarcia.demo.models.user;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.demo.constraints.NicknameConstraint;

/**
 * Profile form.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
public class ProfileForm
{
    private Integer id;
    @NotEmpty
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String nickname;
    @Size(max = 64)
    @Column(name = "avatar")
    private String avatar;
    
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