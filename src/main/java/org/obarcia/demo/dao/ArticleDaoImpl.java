package org.obarcia.demo.dao;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
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
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public ListPagination<Article> getArticles(int page, int perPage, String tag, String type)
    {
        // Pagination
        ListPagination list = new ListPagination();
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
        Root<Article> articleRoot = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(articleRoot.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        if (type != null && !type.equals("all")) {
            predicates.add(builder.equal(articleRoot.get("type"), type));
        }
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteriaCount.where(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(articleRoot.get("publish")));
        
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
        ListPagination list = new ListPagination();
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
        Root<Article> articleRoot = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(articleRoot.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        predicates.add(
            builder.or(
                builder.like(articleRoot.<String>get("title"), "%[" + search + "]%"),
                builder.like(articleRoot.<String>get("description"), "%[" + search + "]%"),
                builder.like(articleRoot.<String>get("content"), "%[" + search + "]%")
            )
        );
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteriaCount.where(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(articleRoot.get("publish")));
        
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
        Root<Article> articleRoot = criteria.from(Article.class);
        
        // Where
        List<Predicate> predicates = new LinkedList<>();
        if (tag != null && !tag.equals("games")) {
            predicates.add(builder.like(articleRoot.<String>get("tags"), "%[" + tag.toUpperCase() + "]%"));
        }
        if (type != null && !type.equals("all")) {
            predicates.add(builder.equal(articleRoot.get("type"), type));
        }
        predicates.add(builder.equal(articleRoot.get("important"), true));
        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        criteria.where(predArray);
        
        criteria.orderBy(builder.desc(articleRoot.get("publish")));
        
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
        // TODO: Implement
        return null;
    }
    @Override
    @Transactional
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        // Pagination
        ListPagination list = new ListPagination();
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
        // TODO: Buscar por el artículo
        criteriaCount.where(builder.equal(commentRoot.get("article").get("id"), id));
        criteria.where(builder.equal(commentRoot.get("article").get("id"), id));
        
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
    public Article getArticle(int id)
    {
        return sessionFactory.getCurrentSession().get(Article.class, id);
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
        } catch (Exception e) {
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
        } catch (Exception e) {
        }
        
        return false;
    }
}