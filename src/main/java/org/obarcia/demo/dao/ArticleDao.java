package org.obarcia.demo.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.ArticleSimple;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.article.CommentLite;

/**
 * DAO de artículos.
 * 
 * @author obarcia
 */
public interface ArticleDao
{
    /**
     * DataTables: Devuelve el listado de artículos en función a los parámetros
     * indicados por la petición DataTables.
     * @param req Instancia de la petición.
     * @return Listado de artículos.
     */
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req);
    /**
     * DataTables: Devuelve el listado de comentarios en función a los parámetros
     * indicados por la petición DataTables.
     * @param id Identificador del artículo.
     * @param req Instancia de la petición.
     * @return Listado de comentarios.
     */
    public DataTablesResponse<CommentLite> getCommentsLite(Integer id, DataTablesRequest req);
    /**
     * Devuelve un listado de artículos.
     * @param page Página.
     * @param perPage Registros por página.
     * @param tag Tipo de etiqueta.
     * @param type Tipo de artículo.
     * @return Listado de artículos
     */
    public ListPagination<ArticleSimple> getArticles(int page, int perPage, String tag, String type);
    /**
     * Búsqueda de artículos.
     * @param page Página.
     * @param perPage Registros por página.
     * @param tag Tipo de etiqueta.
     * @param search Texto de búsqueda.
     * @return Listado de artículos
     */
    public ListPagination<ArticleSimple> getArticlesSearch(int page, int perPage, String tag, String search);
    /**
     * Devuelve un listado de artículos marcados como imporatantes.
     * @param tag Etiqueta de búsqueda.
     * @param type Tipo de artículo
     * @return Listado de artículos.
     */
    public List<ArticleSimple> getArticlesImportant(String tag, String type, int count);
    /**
     * Devuelve un listado de artículos ordenados por número de comentarios.
     * De más comentado a menos.
     * @param tag Etiqueta de búsqueda.
     * @param count Número de registros.
     * @return Listado de artículos.
     */
    public List<ArticleSimple> getArticlesMoreComments(String tag, int count);
    /**
     * Devuelve un listado de comentarios de un artículo.
     * @param id Identificador del artículo.
     * @param page Página.
     * @param perPage Registros por página.
     * @return Listado de comentarios.
     */
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    /**
     * Devuelve el total de comentarios de un artículo.
     * @param id Identificador del artículo.
     * @return Total de artículos.
     */
    public int getCommentsCount(int id);
    /**
     * Devuelve un listado de los últimos comentarios.
     * @param tag Etiqueta.
     * @param count Número de registros.
     * @return Listado de comentarios.
     */
    public List<Comment> getLastComments(String tag, int count);
    /**
     * Devuelve un listado de los últimos comentarios de un usuario.
     * @param id Identificador del usuario.
     * @param count Número de registros.
     * @return Listado de comentarios.
     */
    public List<Comment> getLastCommentsByUser(int id, int count);
    /**
     * Devuelve un artículos por su identificador.
     * @param id Identificador del artículo.
     * @return Instancia del artículo o null si no lo encuentra.
     */
    public Article getArticle(int id);
    /**
     * Devuelve un artículos por su título.
     * @param title Título.
     * @return Instancia del artículo o null si no lo encuentra.
     */
    public Article getArticleByTitle(String title);
    /**
     * Devuelve un comentario por su identificador.
     * @param id Identificador.
     * @return  Instancia del comentario o null si no lo encuentra.
     */
    public Comment getComment(int id);
    /**
     * Guarda un artículo.
     * @param article Instancia del artículo.
     * @throws HibernateException
     */
    public void save(Article article) throws HibernateException;
    /**
     * Guarda un comentario.
     * @param comment Instancia del comentario.
     * @throws HibernateException
     */
    public void save(Comment comment) throws HibernateException;
}