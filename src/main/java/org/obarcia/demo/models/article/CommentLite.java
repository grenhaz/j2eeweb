package org.obarcia.demo.models.article;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserLite;

/**
 * Comentario de un artículo (Lite).
 * 
 * @author obarcia
 */
@Entity
@Table(name = "comment")
public class CommentLite
{
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    /**
     * Identificador.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * Contenido.
     */
    @NotEmpty
    @Size(max = 512)
    @Column(name = "content")
    private String content;
    /**
     * Fecha de publicación.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish")
    private Date publish;
    /**
     * Si el comentario ha sido borrado
     */
    @Column(name = "erased")
    private Boolean erased = Boolean.FALSE;
    /**
     * Artículo.
     */
    @Column(name = "id_article")
    private Integer id_article;
    /**
     * Usuario.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private UserLite user;
    
    /**
     * Devuelve el contenido reducido.
     * @return Contenido reducido.
     */
    public String getShortContent()
    {
        if (content.length() > 83) {
            return content.substring(0, 80) + "...";
        }
        
        return content;
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
    public String getContent()
    {
        return content;
    }
    public void setContent(String value)
    {
        content = value;
    }
    public Date getPublish()
    {
        return publish;
    }
    public void setPublish(Date value)
    {
        publish = value;
    }
    public Integer getIdArticle()
    {
        return id_article;
    }
    public void setIdArticle(Integer value)
    {
        id_article = value;
    }
    public UserLite getUser()
    {
        return user;
    }
    public void setUser(UserLite value)
    {
        user = value;
    }
    public Boolean getErased()
    {
        return erased;
    }
    public void setErased(Boolean value)
    {
        erased = value;
    }
}