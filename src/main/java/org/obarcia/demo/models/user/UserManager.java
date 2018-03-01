package org.obarcia.demo.models.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.obarcia.demo.components.hibernate.HibernateConnector;
import org.obarcia.demo.models.article.Article;

/**
 *
 * @author obarcia
 */
public class UserManager
{
    private static final Logger LOGGER = Logger.getLogger(HibernateConnector.class.getName());
    private static UserManager SINGLETON;
    
    /**
     * Patrón SINGLETÓN.
     * @return Instancia del SINGLETÓN.
     */
    public static synchronized UserManager getInstance()
    {
        if (SINGLETON == null) {
            SINGLETON = new UserManager();
        }
 
        return SINGLETON;
    }
    /**
     * Devuelve el usuario por su nombre.
     * @param username Nombre del usuario.
     * @return Instancia del usuario.
     */
    public User getUserByName(String username)
    {
        Session session = HibernateConnector.getInstance().getSession();
        User user = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            user = (User)criteria.uniqueResult();
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return user;
    }
    public boolean save(User user)
    {
        return HibernateConnector.getInstance().save(user);
    }
}
