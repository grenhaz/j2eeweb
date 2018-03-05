package org.obarcia.demo.controllers;

import java.util.Date;
import javax.validation.Valid;
import org.obarcia.demo.exceptions.ArticleNotFoundException;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.article.CommentForm;
import org.obarcia.demo.models.user.AccountDetails;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.services.ArticleService;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Paginación con N páginas => TEST
// TODO: Buscador por texto (Resultados y paginación)
// TODO: Documento README Completo: Con el FIX del JBOSS
// TODO: Navegador de los artículos: Splash de refresco => TEST
// TODO: Header: Logo
// XXX: Off: Url back en el login
// XXX: Artículo: Utilizar ajax para el proceso de añadir un artículo
// XXX: Crear datos de demo inicialmente (Incluir más)
// XXX: Uso de varios idiomas
// XXX: Diferentes estilos por tag
/**
 * Controlador de la web.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/")
public class WebController
{
    // TODO: Off: Usar placeholders
    private final int ARTICLES_PER_PAGE = 10;
    private final int COMMENTS_PER_PAGE = 10;

    /**
     * Instancia del servicio de usuarios.
     */
    @Autowired
    private UserService userService;
    /**
     * Instancia del servicio de artículos.
     */
    @Autowired
    private ArticleService articleService;
    
    /**
     * Portada.
     * @return Vista resultante.
     */
    @GetMapping("/")
    public ModelAndView actionIndex()
    {
        return getIndex("games");
    }
    /**
     * Portada por etiqueta.
     * @param tag Etiqueta.
     * @return Vista resultante.
     */
    @GetMapping("/web/{tag}")
    public ModelAndView actionIndexTag(
            @PathVariable("tag") String tag)
    {
        return getIndex(tag);
    }
    /**
     * Devuelve la vista a partir de la etiqueta.
     * @param tag Etiqueta.
     * @return Vista resultante.
     */
    private ModelAndView getIndex(String tag)
    {
        return new ModelAndView("articles/index")
                .addObject("tag",           tag)
                .addObject("importants",    articleService.getArticlesImportants(tag))
                .addObject("guides",        articleService.getArticlesByType(tag, "guide", 3))
                .addObject("reviews",       articleService.getArticlesByType(tag, "review", 4))
                .addObject("specials",      articleService.getArticlesByType(tag, "special", 3))
                .addObject("moreComments",  articleService.getArticlesMoreComments(tag, 8))
                .addObject("lastComments",  articleService.getLastComments(tag, 5));
    }
    /**
     * Búsqueda de artículos.
     * @param tag Etiqueta.
     * @param text Texto de búsqueda.
     * @return Vista resultante.
     */
    @GetMapping("/web/{tag}/search")
    public ModelAndView actionArticlesSearch(
            @PathVariable("tag") String tag,
            @RequestParam(value = "t", required = true) String text)
    {
        return new ModelAndView("articles/search")
                .addObject("tag",           tag)
                .addObject("search",        text);
    }
    /**
     * Listado de artículos.
     * @param tag Etiqueta.
     * @param type Tipo.
     * @return Vista resultante.
     */
    @GetMapping("/web/{tag}/{type}")
    public ModelAndView actionArticlesType(
            @PathVariable("tag") String tag,
            @PathVariable("type") String type)
    {
        return new ModelAndView("articles/articles")
                .addObject("tag",           tag)
                .addObject("importants",    articleService.getArticlesImportants(tag, type))
                .addObject("type",          type);
    }
    /**
     * Página de un artículo.
     * @param id Identificador del artículo.
     * @return Vista resultante.
     * @throws ArticleNotFoundException 
     */
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id) throws ArticleNotFoundException
    {
        Article model = articleService.getArticle(id);
        if (model != null && model.getActive().equals(Boolean.TRUE)) {
            return new ModelAndView("articles/article")
                    .addObject("comment",   new CommentForm())
                    .addObject("model",     model);
        } else {
            throw new ArticleNotFoundException();
        }
    }
    /**
     * Procesamiento de añadir un comentario a un artículo.
     * @param id Identificador del artículo.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @return Vista resultante.
     * @throws ArticleNotFoundException
     * @throws SaveException 
     */
    @PostMapping("/article/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionArticle(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("comment") CommentForm form,
            BindingResult result) throws ArticleNotFoundException, SaveException
    {
        Article model = articleService.getArticle(id);
        if (model != null && model.getActive().equals(Boolean.TRUE)) {
            if (!result.hasErrors()) {
                int userId = ((AccountDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
                User user = userService.getUserById(userId);
                if (user != null) {
                    Comment comment = new Comment();
                    comment.setArticle(model);
                    comment.setUser(user);
                    comment.setContent(form.getContent());
                    comment.setPublish(new Date());
                    if (articleService.save(comment)) {
                        return new ModelAndView("redirect:/article/" + id);
                    } else {
                        throw new SaveException();
                    }
                } else {
                    throw new SaveException();
                }
            }

            return new ModelAndView("articles/article")
                    .addObject("comment",   form)
                    .addObject("model",     model);
        } else {
            throw new ArticleNotFoundException();
        }
    }
    // ****************************************
    // AJAX
    // ****************************************
    /**
     * Listado de comentario.
     * @param id Identificador del artículo.
     * @return Vista resultante.
     */
    @GetMapping("/ajax/comments/{id}")
    public ModelAndView actionCommentsAjax(
            @PathVariable("id") int id)
    {
        return new ModelAndView("articles/comments.ajax")
                .addObject("id",        id)
                .addObject("comments",  articleService.getComments(id, 1, COMMENTS_PER_PAGE));
    }
    /**
     * Listado de comentarios.
     * @param id Identificador del artículo.
     * @param page Página.
     * @return Vista resultante.
     */
    @GetMapping("/ajax/comments/{id}/{page}")
    public ModelAndView actionCommentsAjax(
            @PathVariable("id") int id, 
            @PathVariable("page") int page)
    {
        return new ModelAndView("articles/comments.ajax")
                .addObject("id",        id)
                .addObject("comments",  articleService.getComments(id, page, COMMENTS_PER_PAGE));
    }
    /**
     * Listado de artículos.
     * @param tag Etiqueta.
     * @param type Tipo.
     * @param menu Si se muestra el menú o no.
     * @return Vista resultante.
     */
    @GetMapping("/ajax/{tag}/{type}")
    public ModelAndView actionArticlesAjax(
            @PathVariable("tag") String tag, 
            @PathVariable("type") String type,
            @RequestParam(value = "m", required = false) Boolean menu)
    {
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag",       tag)
                .addObject("menu",      menu)
                .addObject("articles",  articleService.getArticlesAll(1, ARTICLES_PER_PAGE, tag, type));
    }
    /**
     * Listado de artículos.
     * @param tag Etiqueta.
     * @param type Tipo.
     * @param menu Si se muestra el menú o no.
     * @param page Página.
     * @return Vista resultante.
     */
    @GetMapping("/ajax/{tag}/{type}/{page}")
    public ModelAndView actionArticlesAjax(
            @PathVariable("tag") String tag, 
            @PathVariable("type") String type, 
            @PathVariable("page") int page,
            @RequestParam(value = "m", required = false) Boolean menu)
    {
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag",       tag)
                .addObject("menu",      menu)
                .addObject("articles",  articleService.getArticlesAll(page, ARTICLES_PER_PAGE, tag, type));
    }
}