package org.obarcia.demo.models;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.obarcia.demo.components.hibernate.HibernateConnector;

/**
 *
 * @author Heck
 */
public class ArticleManager
{
    private static final Logger LOGGER = Logger.getLogger(HibernateConnector.class.getName());
    private static ArticleManager SINGLETON;
    
    /**
     * Patrón SINGLETÓN.
     * @return Instancia del SINGLETÓN.
     */
    public static synchronized ArticleManager getInstance()
    {
        if (SINGLETON == null) {
            SINGLETON = new ArticleManager();
        }
 
        return SINGLETON;
    }
    public List getArticles()
    {
        List models = null;
        Session session = null;
        
        try {
            session = HibernateConnector.getInstance().getSession();
            models = session
                .createQuery("FROM " + Article.class.getCanonicalName())
                .list();
        } catch(Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return models;
    }
    public Article getArticle(int id)
    {
        return (Article)HibernateConnector.getInstance().get(Article.class, id);
    }
}
