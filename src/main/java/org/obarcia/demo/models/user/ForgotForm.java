package org.obarcia.demo.models.user;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Forgot password form.
 * 
 * @author obarcia
 */
public class ForgotForm
{
    @NotEmpty(message = "{error.NotEmpty}")
    @Email(message = "{error.Email}")
    @Size(max = 128)
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
