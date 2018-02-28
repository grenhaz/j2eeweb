package org.obarcia.demo.models.article;

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
@Table(name = "article")
public class Article {
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotEmpty
    @Column(name = "type")
    private String type;
    @NotEmpty
    @Column(name = "title")
    private String title;
    @NotEmpty
    @Column(name = "description")
    private String description;
    @NotEmpty
    @Column(name = "content")
    private String content;
    @NotEmpty
    @Column(name = "publish")
    private Timestamp publish;
    @Column(name = "tags")
    private String tags;
    @Column(name = "important")
    private String important;
    @Column(name = "puntuation")
    private Double puntuation;
    
    public int getId()
    {
        return id;
    }
    public void setId(int value)
    {
        id = value;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String value)
    {
        type = value;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String value)
    {
        title = value;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String value)
    {
        description = value;
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
    public String getTags()
    {
        return tags;
    }
    public void setTags(String value)
    {
        tags = value;
    }
    public String getImportant()
    {
        return important;
    }
    public void setImportant(String value)
    {
        important = value;
    }
    public Double getPuntuation()
    {
        return puntuation;
    }
    public void setPuntuation(Double value)
    {
        puntuation = value;
    }
    public int getComments()
    {
        // TODO: Contabilizar el número de comentarios del artículo
        return 0;
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
}