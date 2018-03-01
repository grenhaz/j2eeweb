package org.obarcia.demo.models.user;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Register form.
 * 
 * @author obarcia
 */
public class RegisterForm
{
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    
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
}
