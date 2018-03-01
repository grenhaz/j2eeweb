package org.obarcia.demo.models.article;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.obarcia.demo.components.hibernate.HibernateConnector;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.user.User;

/**
 * Manager de los artículos.
 * 
 * @author obarcia
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
    /**
     * Devuelve el listado de artículos.
     * @param page Número de página.
     * @param perPage Registros por página.
     * @return  Listado de articulos.
     */
    public ListPagination getArticlesAll(int page, int perPage)
    {
        return getArticlesAll(page, perPage, null, null);
    }
    /**
     * Devuelve el listado de artículos.
     * @param page Número de página.
     * @param perPage Registros por página.
     * @param type Tipo de artículos.
     * @return  Listado de articulos.
     */
    public ListPagination getArticlesAll(int page, int perPage, String type)
    {
        return getArticlesAll(page, perPage, type, null);
    }
    /**
     * Devuelve el listado de artículos.
     * @param page Número de página.
     * @param perPage Registros por página.
     * @param type Tipo de artículos.
     * @param tag Tag de los artículos.
     * @return  Listado de articulos.
     */
    public ListPagination getArticlesAll(int page, int perPage, String type, String tag)
    {
        ListPagination list = new ListPagination();
        list.setType(type != null ? type : "all");
        list.setTag(tag != null ? tag : "games");
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);

        Session session = HibernateConnector.getInstance().getSession();
        try {        
            Criteria criteria = session.createCriteria(Article.class);
            if (type != null && !type.equals("all")) {
                criteria.add(Restrictions.eq("type", type));
            }
            if (tag != null && !tag.equals("games")) {
                criteria.add(Restrictions.like("tags", "%[" + tag.toUpperCase() + "]%"));
            }

            // Total
            criteria.setProjection(Projections.rowCount());
            List rowCount = criteria.list();
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
            list.setRecords(criteria.list());
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return list;
    }
    /**
     * Obtener los últimos destacados.
     * @param tag Etiqueta de filtrado.
     * @return Últimos destacados.
     */
    public List getArticlesImportants(String tag)
    {
        Session session = HibernateConnector.getInstance().getSession();
        List models = null;
        try {
            Criteria criteria = session.createCriteria(Article.class);
            criteria.add(Restrictions.eq("important", true));
            if (tag != null && !tag.equals("games")) {
                criteria.add(Restrictions.like("tags", "%[" + tag.toUpperCase() + "]%"));
            }
            criteria.addOrder(Order.desc("publish"));
            criteria.setMaxResults(3);
            models = criteria.list();
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return models;
    }
    /**
     * Devuelve un listado de los últimos articulos por tag y tipo.
     * @param tag Tag.
     * @param type Tipo de artículo.
     * @param count Límite de artículos.
     * @return 
     */
    public List getArticlesByType(String tag, String type, int count)
    {
        Session session = HibernateConnector.getInstance().getSession();
        List models = null;
        try {
            Criteria criteria = session.createCriteria(Article.class);
            criteria.add(Restrictions.eq("type", type));
            if (tag != null && !tag.equals("games")) {
                criteria.add(Restrictions.like("tags", "%[" + tag.toUpperCase() + "]%"));
            }
            criteria.addOrder(Order.desc("publish"));
            criteria.setMaxResults(4);
            models = criteria.list();
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return models;
    }
    /**
     * Obtener los últimos más comentados.
     * @param tag Etiqueta de filtrado.
     * @return Últimos más comentados.
     */
    public List getArticlesMoreComments(String tag)
    {
        Session session = HibernateConnector.getInstance().getSession();
        List models = null;
        try {
            // TODO: Obtener los más vistos
            Criteria criteria = session.createCriteria(Article.class);
            if (tag != null && !tag.equals("games")) {
                criteria.add(Restrictions.like("tags", "%[" + tag.toUpperCase() + "]%"));
            }
            criteria.addOrder(Order.desc("publish"));
            criteria.setMaxResults(5);

            session = HibernateConnector.getInstance().getSession();
            models = criteria.list();
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return models;
    }
    /**
     * Devuelve un artículo por su identificador.
     * @param id Identificador.
     * @return Instancia del artículo.
     */
    public Article getArticle(int id)
    {
        return (Article)HibernateConnector.getInstance().get(Article.class, id);
    }
    /**
     * Devuelve el listado de comentarios paginado.
     * @param id Identificador del artículo.
     * @param page Página.
     * @param perPage Registros por página.
     * @return Listado de comentarios paginado.
     */
    public ListPagination getComments(int id, int page, int perPage)
    {
        ListPagination list = new ListPagination();
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        
        Session session = HibernateConnector.getInstance().getSession();
        try {
            Criteria criteria = session.createCriteria(Comment.class);
            criteria.add(Restrictions.eq("article.id", id));
            
            // Total
            criteria.setProjection(Projections.rowCount());
            List rowCount = criteria.list();
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
            list.setRecords(criteria.list());
        } catch (Exception sqlException) {
            LOGGER.log(Level.SEVERE, sqlException.toString(), sqlException);
        }
        
        return list;
    }
    public boolean save(Article article)
    {
        return HibernateConnector.getInstance().save(article);
    }
    public boolean save(Comment comment)
    {
        return HibernateConnector.getInstance().save(comment);
    }
}