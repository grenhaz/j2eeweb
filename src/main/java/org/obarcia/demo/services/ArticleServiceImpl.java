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
        return articleDao.getArticles(page, perPage, null, type);
    }
    @Override
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type)
    {
        return articleDao.getArticles(page, perPage, tag, type);
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
    public List getArticlesMoreComments(String tag)
    {
        return articleDao.getArticlesMoreComments(tag, 5);
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