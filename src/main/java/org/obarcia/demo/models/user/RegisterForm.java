package org.obarcia.demo.models.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Register form.
 * 
 * @author obarcia
 */
public class RegisterForm
{
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Email
    private String emailRepeat;
    @NotEmpty
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
