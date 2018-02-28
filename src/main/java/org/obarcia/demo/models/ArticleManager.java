package org.obarcia.demo.models;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    public Criteria getCriteria()
    {
        Session session = HibernateConnector.getInstance().getSession();
        return session.createCriteria(Article.class);
    }
    public ListPagination getArticlesAll(int page, int perPage)
    {
        return getArticlesAll(page, perPage, null);
    }
    public ListPagination getArticlesAll(int page, int perPage, String type)
    {
        ListPagination list = new ListPagination();
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        
        Criteria criteria = getCriteria();
        if (type != null && !type.equals("all")) {
            criteria.add(Restrictions.eq("type", type));
        }
        
        // Total
        criteria.setProjection(Projections.rowCount());
        List rowCount = getArticles(criteria);
        if (rowCount != null) {
            list.setTotal(((Long)rowCount.get(0)).intValue());
        } else {
            list.setTotal(0);
        }
        
        // Records
        criteria.addOrder(Order.desc("publish"));
        criteria.setProjection(null);
        criteria.setFirstResult(list.getOffset());
        criteria.setMaxResults(list.getLimit());
        list.setRecords(getArticles(criteria));
        
        return list;
    }
    public List getArticlesImportants()
    {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("important", "S"));
        criteria.addOrder(Order.desc("publish"));
        criteria.setMaxResults(3);
        return getArticles(criteria);
    }
    public List getArticlesGuides()
    {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("type", "guide"));
        criteria.addOrder(Order.desc("publish"));
        criteria.setMaxResults(3);
        return getArticles(criteria);
    }
    public List getArticlesReviews()
    {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("type", "review"));
        criteria.addOrder(Order.desc("publish"));
        criteria.setMaxResults(4);
        return getArticles(criteria);
    }
    public List getArticles(Criteria criteria)
    {
        List models = null;
        Session session = null;
        
        try {
            session = HibernateConnector.getInstance().getSession();
            models = criteria.list();
        } catch(Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        } finally {
            if(session != null) {
                //session.close();
            }
        }

        return models;
    }
    public Article getArticle(int id)
    {
        // TODO: Exception NotFound
        return (Article)HibernateConnector.getInstance().get(Article.class, id);
    }
}