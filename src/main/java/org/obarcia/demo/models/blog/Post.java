package org.obarcia.demo.models.blog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Blog Post.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "post")
public class Post {
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotEmpty
    @Column(name = "title")
    private String title;
    @NotEmpty
    @Column(name = "content")
    private String content;
    @NotEmpty
    @Column(name = "publish")
    private Timestamp publish;
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String value)
    {
        title = value;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public Timestamp getPublish()
    {
        return publish;
    }
    public void setPublish(Timestamp value)
    {
        publish = value;
    }
    /**
     * Devuelve la fecha de publicación formateada.
     * @return Fecha de publicación formateada.
     */
    public String getFormattedPublish()
    {
        if (publish != null) {
            return format.format(publish);
        }
        
        return "";
    }
    /**
     * Devuelve el contenido recortado.
     * @return Contenido recortado.
     */
    public String getShortContent()
    {
        // Reducir el contenido
        if (content != null && content.length() > 128) {
            return content.substring(0, 125) + "...";
        }
        
        return content;
    }
}