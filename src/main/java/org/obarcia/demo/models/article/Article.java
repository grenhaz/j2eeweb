package org.obarcia.demo.models.article;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Article entity.
 * 
 * @author obarcia
 */
@Entity
@Table(name = "article")
public class Article
{
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotEmpty
    @Size(max = 12)
    @Column(name = "type")
    private String type;
    @NotEmpty
    @Size(max = 250)
    @Column(name = "title")
    private String title;
    @NotEmpty
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @NotEmpty
    @Column(name = "content")
    private String content;
    @NotEmpty
    @Column(name = "publish")
    private Timestamp publish;
    @NotEmpty
    @Size(max = 128)
    @Column(name = "tags")
    private String tags;
    @NotEmpty
    @Size(max = 1)
    @Column(name = "important")
    private String important;
    @Column(name = "puntuation")
    private Double puntuation;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "article")
    private Set<Comment> comments = new HashSet<>();
    
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
    public Set<Comment> getComments()
    {
        return comments;
    }
    public int getCommentsCount()
    {
        return comments.size();
    }
    /**
     * Devuelve las etiquetas formateadas para visualización.
     * @return Etiquetas formateadas para visualización.
     */
    public String getFormattedTags()
    {
        if (tags != null) {
            return tags
                .replaceAll("\\]\\[", " ")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
        }
        
        return "";
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