package org.obarcia.demo.services;

import java.util.List;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.article.Comment;

/**
 * Servicio de artículos y comentarios.
 * 
 * @author obarcia
 */
public interface ArticleService
{
    /**
     * DataTables: Devuelve el listado de artículos en función a los parámetros
     * indicados por la petición DataTables.
     * @param req Instancia de la petición.
     * @return Listado de artículos.
     */
    public DataTablesResponse<ArticleLite> getArticlesLite(DataTablesRequest req);
    /**
     * Devuelve un listado de artículos.
     * @param page Página.
     * @param perPage Registros por página.
     * @return Listado de artículos
     */
    public ListPagination<Article> getArticlesAll(int page, int perPage);
    /**
     * Devuelve un listado de artículos.
     * @param page Página.
     * @param perPage Registros por página.
     * @param type Tipo de artículo.
     * @return Listado de artículos
     */
    public ListPagination<Article> getArticlesAll(int page, int perPage, String type);
    /**
     * Devuelve un listado de artículos.
     * @param page Página.
     * @param perPage Registros por página.
     * @param tag Tipo de etiqueta.
     * @param type Tipo de artículo.
     * @return Listado de artículos
     */
    public ListPagination<Article> getArticlesAll(int page, int perPage, String tag, String type);
    /**
     * Devuelve un listado de artículos marcados como imporatantes.
     * @param tag Etiqueta de búsqueda.
     * @return Listado de artículos.
     */
    public List getArticlesImportants(String tag);
    /**
     * Devuelve un listado de artículos marcados como imporatantes.
     * @param tag Etiqueta de búsqueda.
     * @param type Tipo de artículo
     * @return Listado de artículos.
     */
    public List getArticlesImportants(String tag, String type);
    /**
     * Devuelve un listado de artículos por tipo.
     * @param tag Etiqueta.
     * @param type Tipo de artículo.
     * @param count Número de registros.
     * @return Listado de artículos.
     */
    public List getArticlesByType(String tag, String type, int count);
    /**
     * Devuelve un listado de artículos ordenados por número de comentarios.
     * De más comentado a menos.
     * @param tag Etiqueta de búsqueda.
     * @param count Número de registros.
     * @return Listado de artículos.
     */
    public List getArticlesMoreComments(String tag, int count);
    /**
     * Devuelve un listado de comentarios de un artículo.
     * @param id Identificador del artículo.
     * @param page Página.
     * @param perPage Registros por página.
     * @return Listado de comentarios.
     */
    public ListPagination<Comment> getComments(int id, int page, int perPage);
    /**
     * Devuelve un listado de los últimos comentarios.
     * @param tag Etiqueta.
     * @param count Número de registros.
     * @return Listado de comentarios.
     */
    public List<Comment> getLastComments(String tag, int count);
    /**
     * Devuelve un artículos por su identificador.
     * @param id Identificador del artículo.
     * @return Instancia del artículo.
     */
    public Article getArticle(int id);
    /**
     * Devuelve un artículos por su título.
     * @param title Título.
     * @return Instancia del artículo.
     */
    public Article getArticleByTitle(String title);
    /**
     * Guarda un artículo.
     * @param article Instancia del artículo.
     * @return true si la operación fué correcta, false en caso contrario.
     */
    public boolean save(Article article);
    /**
     * Guarda un comentario.
     * @param comment Instancia del comentario.
     * @return true si la operación fué correcta, false en caso contrario.
     */
    public boolean save(Comment comment);
}