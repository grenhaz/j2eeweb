package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.dao.ArticleDao;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.article.CommentLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req)
    {
        return articleDao.getArticlesLite(req);
    }
    @Override
    public DataTablesResponse<CommentLite> getCommentsLite(Integer id, DataTablesRequest req)
    {
        return articleDao.getCommentsLite(id, req);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage)
    {
        return articleDao.getArticles(page, perPage, null, null);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type)
    {
        return articleDao.getArticles(page, perPage, null, type);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type)
    {
        return articleDao.getArticles(page, perPage, tag, type);
    }
    @Override
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search)
    {
        return articleDao.getArticlesSearch(page, perPage, tag, search);
    }
    @Override
    public List getArticlesImportants(String tag)
    {
        return articleDao.getArticlesImportant(tag, null, 3);
    }
    @Override
    public List getArticlesImportants(String tag, String type)
    {
        return articleDao.getArticlesImportant(tag, type, 3);
    }
    @Override
    public List getArticlesByType(String tag, String type, int count)
    {
        return articleDao.getArticles(0, count, tag, type).getRecords();
    }
    @Override
    public List getArticlesMoreComments(String tag, int count)
    {
        return articleDao.getArticlesMoreComments(tag, count);
    }
    @Override
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        return articleDao.getComments(id, page, perPage);
    }
    @Override
    public List<Comment> getLastComments(String tag, int count)
    {
        return articleDao.getLastComments(tag, count);
    }
    @Override
    public List<Comment> getLastCommentsByUser(int id, int count)
    {
        return articleDao.getLastCommentsByUser(id, count);
    }
    @Override
    public Article getArticle(int id)
    {
        return articleDao.getArticle(id);
    }
    @Override
    public Comment getComment(int id)
    {
        return articleDao.getComment(id);
    }
    @Override
    public Article getArticleByTitle(String title)
    {
        return articleDao.getArticleByTitle(title);
    }
    @Override
    public boolean save(Article article)
    {
        return articleDao.save(article);
    }
    @Override
    public boolean save(Comment comment)
    {
        return articleDao.save(comment);
    }
}