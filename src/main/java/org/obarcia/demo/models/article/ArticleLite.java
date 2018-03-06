package org.obarcia.demo.models.article;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Artículo (Lite).
 * 
 * @author obarcia
 */
@Entity
@Table(name = "article")
public class ArticleLite
{
    /**
     * Formato de fecha
     */
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    /**
     * Identificador.
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Tipo.
     */
    @Column(name = "type")
    private String type;
    /**
     * Título.
     */
    @Column(name = "title")
    private String title;
    /**
     * Descripción.
     */
    @Column(name = "description")
    private String description;
    /**
     * Fecha de publicación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish")
    private Date publish;
    /**
     * Etiquetas.
     */
    @Column(name = "tags")
    private String tags;
    /**
     * Si es importante / destacado.
     */
    @Column(name = "importat")
    private Boolean important;
    /**
     * Puntuación.
     */
    @Column(name = "score")
    private Double score;
    /**
     * Si está activo o no.
     */
    @Column(name = "active")
    private Boolean active;
    
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
            return FORMAT.format(publish);
        }
        
        return "";
    }
    // ******************************************
    // GETTER & SETTER
    // ******************************************
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
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
    public Date getPublish()
    {
        return publish;
    }
    public void setPublish(Date value)
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
    public Boolean getImportant()
    {
        return important;
    }
    public void setImportant(Boolean value)
    {
        important = value;
    }
    public Double getScore()
    {
        return score;
    }
    public void setScore(Double value)
    {
        score = value;
    }
    public Boolean getActive()
    {
        return active;
    }
    public void setActive(Boolean value)
    {
        active = value;
    }
}