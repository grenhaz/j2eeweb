package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.dao.ArticleDao;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author obarcia
 */
@Service
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    private ArticleDao articleDao;
    
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage)
    {
        return articleDao.getArticles(page, perPage, null, null);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type)
    {
        return articleDao.getArticles(page, perPage, type, null);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type, String tag)
    {
        return articleDao.getArticles(page, perPage, type, tag);
    }
    @Override
    public List getArticlesImportants(String tag)
    {
        // TODO: Implement
        return null;
    }
    @Override
    public List getArticlesByType(String tag, String type, int count)
    {
        return articleDao.getArticles(0, count, type, null).getRecords();
    }
    @Override
    public List getArticlesMoreComments(String tag)
    {
        // TODO: Implement
        return null;
    }
    @Override
    public ListPagination<Comment> getComments(int id, int page, int perPage)
    {
        return articleDao.getComments(id, page, perPage);
    }
    @Override
    public Article getArticle(int id)
    {
        return articleDao.getArticle(id);
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