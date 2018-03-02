package org.obarcia.demo.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public ListPagination<Article> getArticles(int page, int perPage, String type, String tag)
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
        if (type != null && !type.equals("all")) {
            criteria.where(builder.equal(articleRoot.get("type"), type));
            criteriaCount.where(builder.equal(articleRoot.get("type"), type));
        }
        if (tag != null && !tag.equals("games")) {
            // TODO: criteria.where(builder.like(articleRoot.get("tag"), "%" + tag + "%"));
        }
        
        criteria.orderBy(builder.desc(articleRoot.get("publish")));
        
        // Query
        Query<Article> q = sessionFactory.getCurrentSession().createQuery(criteria);
        list.setTotal(sessionFactory.getCurrentSession().createQuery(criteriaCount).getSingleResult().intValue());
        list.setRecords(q.list());
        
        return list;
    }
    @Override
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        // TODO: Implement
        return null;
    }
    @Override
    public Article getArticle(int id)
    {
        return sessionFactory.getCurrentSession().get(Article.class, id);
    }
    @Override
    public Comment getComment(int id)
    {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }
    @Override
    public boolean save(Article article)
    {
        return false;
    }
    @Override
    public boolean save(Comment comment)
    {
        return false;
    }
}