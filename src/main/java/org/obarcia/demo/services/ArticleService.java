package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.Comment;

/**
 *
 * @author obarcia
 */
public interface ArticleService
{
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req);
    public ListPagination<Article> getArticlesAll(int page, int perPage);
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type);
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type);
    public List getArticlesImportants(String tag);
    public List getArticlesImportants(String tag, String type);
    public List getArticlesByType(String tag, String type, int count);
    public List getArticlesMoreComments(String tag, int count);
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    public List<Comment> getLastComments(String tag, int count);
    public Article getArticle(int id);
    public Article getArticleByTitle(String title);
    public boolean save(Article article);
    public boolean save(Comment comment);
}