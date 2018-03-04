package org.obarcia.demo.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.obarcia.demo.components.datatables.DataTablesOrder;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author obarcia
 */
@Repository
public class ArticleDaoImpl implements ArticleDao
{
    private final static Logger LOGGER = Logger.getLogger(ArticleDaoImpl.class.getName());
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req)
    {
        DataTablesResponse<ArticleLite> list = new DataTablesResponse<>();
        
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Count
        CriteriaQuery<Long> criteriaCount = builder.createQuery(Long.class);
        criteriaCount.select(builder.count(criteriaCount.from(ArticleLite.class)));
        
        // Query
        CriteriaQuery<ArticleLite> criteria = builder.createQuery(ArticleLite.class);
        Root<ArticleLite> root = criteria.from(ArticleLite.class);
        
        List<Predicate> predicates = new LinkedList<>();
        
        // Filters by column
        // Id
        if (req.hasColumnSearch("id")) {
            predicates.add(builder.equal(root.get("id"), Integer.parseInt(req.getColumnSearch("id"))));
        }
        // Title
        if (req.hasColumnSearch("tile")) {
            predicates.add(builder.like(root.<String>get("tile"), "%" + req.getColumnSearch("title") + "%"));
        }
        // Description
        if (req.hasColumnSearch("description")) {
            predicates.add(builder.like(root.<String>get("description"), "%" + req.getColumnSearch("description") + "%"));
        }
        // Type
        if (req.hasColumnSearch("type")) {
            predicates.add(builder.equal(root.get("type"), req.getColumnSearch("type")));
        }
        // Tags
        if (req.hasColumnSearch("tags")) {
            predicates.add(builder.like(root.<String>get("tags"), "%[" + req.getColumnSearch("tags") + "]%"));
        }
        // Active
        if (req.hasColumnSearch("active")) {
            predicates.add(builder.equal(root.get("active"), Boolean.valueOf(req.getColumnSearch("active"))));
        }
        // TODO: Publish
        if (req.hasColumnSearch("publish")) {
            predicates.add(builder.equal(root.get("publish"), req.getColumnSearch("publish")));
        }
        
        // Filters general
        if (!req.getSearch().isEmpty()) {
            predicates.add(builder.like(root.<String>get("title"), "%" + req.getSearch() + "%"));
            predicates.add(builder.like(root.<String>get("description"), "%" + req.getSearch() + "%"));
        }
        
        // Where
        if (predicates.size() > 0) {
            Predicate[] predArray = new Predicate[predicates.size()];
            predicates.toArray(predArray);
            criteriaCount.where(builder.or(predArray));
            criteria.where(builder.or(predArray));
        }
        
        // Order
        List<Order> orders = new LinkedList<>();
        for (DataTablesOrder o: req.getOrders()) {
            if (o.getDir() == DataTablesOrder.ORDER_ASC) {
                orders.add(builder.asc(root.get(o.getData())));
            } else {
                orders.add(builder.desc(root.get(o.getData())));
            }
        }
        criteria.orderBy(orders);
        
        // Query
        Query<ArticleLite> q = sessionFactory.getCurrentSession().createQuery(criteria);
        q.setFirstResult(req.getStart()).setMaxResults(req.getLength());
        list.setDraw(req.getDraw());
        list.setRecordsTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecordsFiltered(list.getRecordsTotal());
        list.setData(q.list());
        
        return list;
    }
    @Override
    @Transactional
    public ListPagination<Article> getArticles(int page, int perPage, String tag, String type)
    {
        // Pagination
        ListPagination<Article> list = new ListPagination<>();
        list.setType(type != null ? type : "all");
        list.setTag(tag != null ? tag : "games");
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Count
        CriteriaQuery<Long> criteriaCount = builder.createQuery(Long.class);
        criteriaCount.select(builder.count(criteriaCount.from(Article.class)));
        
        // Query
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(root.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        if (type != null && !type.equals("all")) {
            predicates.add(builder.equal(root.get("type"), type));
        }
        predicates.add(builder.equal(root.get("active"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteriaCount.where(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(root.get("publish")));
        
        // Query
        Query<Article> q = sessionFactory.getCurrentSession().createQuery(criteria);
        q.setFirstResult(list.getOffset()).setMaxResults(list.getLimit());
        list.setTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecords(q.list());
        
        return list;
    }    
    @Override
    @Transactional
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        // Pagination
        ListPagination<Article> list = new ListPagination<>();
        list.setType("all");
        list.setTag(tag != null ? tag : "games");
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Count
        CriteriaQuery<Long> criteriaCount = builder.createQuery(Long.class);
        criteriaCount.select(builder.count(criteriaCount.from(Article.class)));
        
        // Query
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(root.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        predicates.add(
            builder.or(
                builder.like(root.<String>get("title"), "%[" + search + "]%"),
                builder.like(root.<String>get("description"), "%[" + search + "]%"),
                builder.like(root.<String>get("content"), "%[" + search + "]%")
            )
        );
        predicates.add(builder.equal(root.get("active"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteriaCount.where(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(root.get("publish")));
        
        // Query
        Query<Article> q = sessionFactory.getCurrentSession().createQuery(criteria);
        q.setFirstResult(list.getOffset()).setMaxResults(list.getLimit());
        list.setTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecords(q.list());
        
        return list;
    }
    @Override
    @Transactional
    public List<Article> getArticlesImportant(String tag, String type, int count)
    {
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Query
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(root.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        if (type != null && !type.equals("all")) {
            predicates.add(builder.equal(root.get("type"), type));
        }
        predicates.add(builder.equal(root.get("active"), true));
        predicates.add(builder.equal(root.get("important"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(root.get("publish")));
        
        // Query
        return sessionFactory
                .getCurrentSession()
                .createQuery(criteria)
                .setMaxResults(count)
                .list();
    }
    @Override
    @Transactional
    public List<Article> getArticlesMoreComments(String tag, int count)
    {
        // TODO: !!!! Obtener los artículos más vistos / comentados.
        return null;
        /*// Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Query
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Comment> root = criteria.from(Comment.class);
        
        // Select
        criteria.multiselect(root.get("article"));
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(root.get("article").<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        predicates.add(builder.equal(root.get("article").get("active"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteria.where(predArray);
        
        // Group By
        criteria.groupBy(root.get("article").get("id"));
        
        criteria.orderBy(builder.desc(builder.count(root)));
        
        // Query
        return sessionFactory
                .getCurrentSession()
                .createQuery(criteria)
                .setMaxResults(count)
                .list();*/
    }
    @Override
    @Transactional
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        // Pagination
        ListPagination<Comment> list = new ListPagination<>();
        list.setOffset((page - 1) * perPage);
        list.setLimit(perPage);
        
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Count
        CriteriaQuery<Long> criteriaCount = builder.createQuery(Long.class);
        criteriaCount.select(builder.count(criteriaCount.from(Comment.class)));
        
        // Query
        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> commentRoot = criteria.from(Comment.class);
        
        // Where
        Predicate predicate = builder.and(
            builder.equal(commentRoot.get("article").get("id"), id),
            builder.equal(commentRoot.get("article").get("active"), true)
        );
        criteriaCount.where(predicate);
        criteria.where(predicate);
        
        criteria.orderBy(builder.desc(commentRoot.get("publish")));
        
        // Query
        Query<Comment> q = sessionFactory.getCurrentSession().createQuery(criteria);
        q.setFirstResult(list.getOffset()).setMaxResults(list.getLimit());
        list.setTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecords(q.list());
        
        return list;
    }
    @Override
    @Transactional
    public List<Comment> getLastComments(String tag, int count)
    {
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Query
        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> commentRoot = criteria.from(Comment.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(commentRoot.get("article").<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        predicates.add(builder.equal(commentRoot.get("article").get("active"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(commentRoot.get("publish")));
        
        // Query
        return sessionFactory
                .getCurrentSession()
                .createQuery(criteria)
                .setMaxResults(count)
                .list();
    }
    @Override
    @Transactional
    public Article getArticle(int id)
    {
        return sessionFactory.getCurrentSession().get(Article.class, id);
    }
    @Override
    @Transactional
    public Article getArticleByTitle(String title)
    {
        // Criteria
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        
        // Query
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        
        // Where
        criteria.where(builder.equal(root.get("title"), title));
        
        criteria.orderBy(builder.desc(root.get("publish")));
        
        // Query
        return sessionFactory
                .getCurrentSession()
                .createQuery(criteria)
                .setMaxResults(1)
                .uniqueResult();
    }
    @Override
    @Transactional
    public Comment getComment(int id)
    {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }
    @Override
    @Transactional
    public boolean save(Article article)
    {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(article);
            
            return true;
        } catch (HibernateException e) {
        }
        
        return false;
    }
    @Override
    @Transactional
    public boolean save(Comment comment)
    {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(comment);
            
            return true;
        } catch (HibernateException e) {
        }
        
        return false;
    }
}