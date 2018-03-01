package org.obarcia.demo.models.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Forgot password form.
 * 
 * @author obarcia
 */
public class ForgotForm
{
    @NotEmpty
    @Email
    private String username;
    
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String value)
    {
        username = value;
    }
}
