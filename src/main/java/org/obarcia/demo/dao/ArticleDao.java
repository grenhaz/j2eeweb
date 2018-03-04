package org.obarcia.demo.dao;

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
public interface ArticleDao
{
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req);
    public ListPagination<Article> getArticles(int page, int perPage, String tag, String type);
    public ListPagination<Article> getArticlesSearch(int page, int perPage, String tag, String search);
    public List<Article> getArticlesImportant(String tag, String type, int count);
    public List<Article> getArticlesMoreComments(String tag, int count);
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    public List<Comment> getLastComments(String tag, int count);
    public Article getArticle(int id);
    public Article getArticleByTitle(String title);
    public Comment getComment(int id);
    public boolean save(Article article);
    public boolean save(Comment comment);
}