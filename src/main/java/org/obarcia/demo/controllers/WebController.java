package org.obarcia.demo.controllers;

import org.obarcia.demo.exceptions.ArticleNotFoundException;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * Web controller.
 * 
 * @author obarcia
 */
// FIX: Paginación con N páginas
// TODO: Administración: Index (Estadísticas)
// TODO: Administración: Users
// TODO: Administración: Articles
// TODO: Administración: Articles: Comments
// TODO: Administración: Tinymce
// TODO: !!!! Añadir comentarios
// TODO: Usuario: Registro: Validaciones extra y procesamiento
// TODO: Usuario: Forgot password: Procesamiento
// TODO: !!!! Repositorio de imágenes
// TODO: Usuario: Perfil (Cambair contraseña, avatar y nickname)
// TODO: Usuario: Avatar (De donde sacar la imagen)
// TODO: Buscador por texto (Resultados y paginación)
// TODO: !!!! Obtener los artículos más vistos / comentados.
// GFX: Listado de comentarios: Avatar del usuario
// GFX: Header: Logo
// GFX: favicon.ico
// XXX: Security: Extra parameters
// XXX: Header: Buscador
// XXX: Navegador de los artículos: Splash de refresco
// XXX: Crear datos de demo inicialmente (Incluir más)
// XXX: Cambiar de idioma
// XXX: Diferentes estilos por tag

@Controller
@RequestMapping("/")
public class WebController
{
    @Autowired
    private ArticleService articleService;
    
    // ****************************************
    // WEB
    // ****************************************
    @GetMapping("/")
    public ModelAndView actionIndex()
    {
        return getIndex("games");
    }
    @GetMapping("/web/{tag}")
    public ModelAndView actionIndexTag(
            @PathVariable("tag") String tag)
    {
        return getIndex(tag);
    }
    private ModelAndView getIndex(String tag)
    {   
        return new ModelAndView("articles/index")
                .addObject("tag",           tag)
                .addObject("importants",    articleService.getArticlesImportants(tag))
                .addObject("guides",        articleService.getArticlesByType(tag, "guide", 3))
                .addObject("reviews",       articleService.getArticlesByType(tag, "review", 4))
                .addObject("specials",      articleService.getArticlesByType(tag, "special", 3))
                .addObject("moreComments",  articleService.getArticlesMoreComments(tag));
    }
    @GetMapping("/web/{tag}/search")
    public ModelAndView actionArticlesSearch(
            @PathVariable("tag") String tag,
            @RequestParam(value = "t", required = true) String text)
    {
        return new ModelAndView("articles/search")
                .addObject("tag",           tag)
                .addObject("search",        text);
    }
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
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id) throws ArticleNotFoundException
    {
        Comment comment = new Comment();
        Article model = articleService.getArticle(id);
        if (model != null) {
            return new ModelAndView("articles/article")
                    .addObject("comment",   comment)
                    .addObject("model",     model);
        } else {
            throw new ArticleNotFoundException();
        }
    }
    // ****************************************
    // AJAX
    // ****************************************
    @GetMapping("/ajax/comments/{id}")
    public ModelAndView actionCommentsAjax(
            @PathVariable("id") int id)
    {
        return new ModelAndView("articles/comments.ajax")
                .addObject("id",        id)
                .addObject("comments",  articleService.getComments(id, 1, 10));
    }
    @GetMapping("/ajax/comments/{id}/{page}")
    public ModelAndView actionCommentsAjax(
            @PathVariable("id") int id, 
            @PathVariable("page") int page)
    {
        return new ModelAndView("articles/comments.ajax")
                .addObject("id",        id)
                .addObject("comments",  articleService.getComments(id, page, 10));
    }
    @GetMapping("/ajax/{tag}/{type}")
    public ModelAndView actionArticlesAjax(
            @PathVariable("tag") String tag, 
            @PathVariable("type") String type,
            @RequestParam(value = "m", required = false) Boolean menu)
    {
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag",       tag)
                .addObject("menu",      menu)
                .addObject("articles",  articleService.getArticlesAll(1, 10, tag, type));
    }
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
                .addObject("articles",  articleService.getArticlesAll(page, 10, tag, type));
    }
}