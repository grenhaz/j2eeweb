package org.obarcia.demo.models.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.demo.constraints.EmailEqualConstraint;
import org.obarcia.demo.constraints.EmailUniqueConstraint;
import org.obarcia.demo.constraints.NicknameConstraint;

/**
 * Register form.
 * 
 * @author obarcia
 */
@NicknameConstraint(field = "nickname", message = "{error.nickname.invalid}")
@EmailEqualConstraint(first = "email", second = "emailRepeat", message = "{error.email.repeat}")
public class RegisterForm
{
    @EmailUniqueConstraint(message = "{error.email.unique}")
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
    private String email;
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
    private String emailRepeat;
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "{error.nickname.format}")
    private String nickname;
    
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
    public String getEmailRepeat()
    {
        return emailRepeat;
    }
    public void setEmailRepeat(String value)
    {
        emailRepeat = value;
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
