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
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repeatPassword;
    
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
    public String getRepeatPassword()
    {
        return repeatPassword;
    }
    public void setRepeatPassword(String value)
    {
        repeatPassword = value;
    }
}
