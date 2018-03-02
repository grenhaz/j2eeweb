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
    private String email;
    
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
}
