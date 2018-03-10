package org.obarcia.demo.services;

import java.util.List;
import org.hibernate.HibernateException;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.dao.ArticleDao;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.ArticleSimple;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.article.CommentLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de artículos y comentarios.
 * 
 * @author obarcia
 */
@Service
public class ArticleServiceImpl implements ArticleService
{
    /**
     * Instancia del DAO de artículos.
     */
    @Autowired
    private ArticleDao articleDao;
    
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req)
    {
        return articleDao.getArticlesLite(req);
    }
    @Override
    @Transactional(readOnly = true)
    public DataTablesResponse<CommentLite> getCommentsLite(Integer id, DataTablesRequest req)
    {
        return articleDao.getCommentsLite(id, req);
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage)
    {
        ListPagination<ArticleSimple> list = articleDao.getArticles(page, perPage, null, null);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String type)
    {
        ListPagination<ArticleSimple> list = articleDao.getArticles(page, perPage, null, type);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesAll(int page, int perPage, String tag, String type)
    {
        ListPagination<ArticleSimple> list = articleDao.getArticles(page, perPage, tag, type);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<ArticleSimple> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        ListPagination<ArticleSimple> list = articleDao.getArticlesSearch(page, perPage, tag, search);
        // Obtener el contador de comentarios
        if (list.getRecords() != null) {
            for (ArticleSimple a: list.getRecords()) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesImportants(String tag)
    {
        List<ArticleSimple> list = articleDao.getArticlesImportant(tag, null, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesImportants(String tag, String type)
    {
        List<ArticleSimple> list = articleDao.getArticlesImportant(tag, type, 3);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesByType(String tag, String type, int count)
    {
        List<ArticleSimple> list = articleDao.getArticles(0, count, tag, type).getRecords();
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ArticleSimple> getArticlesMoreComments(String tag, int count)
    {
        List<ArticleSimple> list = articleDao.getArticlesMoreComments(tag, count);
        // Obtener el contador de comentarios
        if (list != null) {
            for (ArticleSimple a: list) {
                a.getCommentsCount();
            }
        }
        
        return list;
    }
    @Override
    @Transactional(readOnly = true)
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        return articleDao.getComments(id, page, perPage);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastComments(String tag, int count)
    {
        return articleDao.getLastComments(tag, count);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getLastCommentsByUser(int id, int count)
    {
        return articleDao.getLastCommentsByUser(id, count);
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticle(int id)
    {
        return articleDao.getArticle(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Comment getComment(int id)
    {
        return articleDao.getComment(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Article getArticleByTitle(String title)
    {
        return articleDao.getArticleByTitle(title);
    }
    @Override
    @Transactional
    public void save(Article article) throws SaveException
    {
        try {
            articleDao.save(article);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
    @Override
    @Transactional
    public void save(Comment comment) throws SaveException
    {
        try {
            articleDao.save(comment);
        } catch (HibernateException e) {
            throw new SaveException();
        }
    }
}