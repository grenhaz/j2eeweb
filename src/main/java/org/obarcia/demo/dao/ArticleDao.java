package org.obarcia.demo.dao;

import java.util.List;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;

/**
 *
 * @author obarcia
 */
public interface ArticleDao
{
    public ListPagination<Article> getArticles(int page, int perPage, String tag, String type);
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search);
    public List<Article> getArticlesImportant(String tag, String type, int count);
    public List<Article> getArticlesMoreComments(String tag, int count);
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    public Article getArticle(int id);
    public Comment getComment(int id);
    public boolean save(Article article);
    public boolean save(Comment comment);
}