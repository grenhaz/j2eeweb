package org.obarcia.demo.models.article;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Comment entity.
 * 
 * @author obarcia
 */
public class CommentForm
{
    @NotEmpty(message = "{error.NotEmpty}")
    @Size(max = 512)
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
