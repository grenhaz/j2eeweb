package org.obarcia.demo.models.blog;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author obarcia
 */
public class CommentForm
{
    private String content;
    
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
}
