package org.obarcia.demo.dao;

import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;

/**
 *
 * @author obarcia
 */
public interface ArticleDao
{
    public ListPagination<Article> getArticles(int page, int perPage, String type, String tag);
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    public Article getArticle(int id);
    public Comment getComment(int id);
    public boolean save(Article article);
    public boolean save(Comment comment);
}