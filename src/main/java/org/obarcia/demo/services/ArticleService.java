package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;

/**
 *
 * @author obarcia
 */
public interface ArticleService
{
    public ListPagination<Article> getArticlesAll(int page, int perPage);
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type);
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type, String tag);
    public List getArticlesImportants(String tag);
    public List getArticlesByType(String tag, String type, int count);
    public List getArticlesMoreComments(String tag);
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    public Article getArticle(int id);
    public boolean save(Article article);
    public boolean save(Comment comment);
}