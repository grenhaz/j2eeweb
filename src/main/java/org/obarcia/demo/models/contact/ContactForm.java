package org.obarcia.demo.models.contact;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Contact form.
 * 
 * @author obarcia
 */
public class ContactForm
{
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    private String subject;
    @NotEmpty
    private String message;
    
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
    public String getSubject()
    {
        return subject;
    }
    public void setSubject(String value)
    {
        subject = value;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String value)
    {
        message = value;
    }
}