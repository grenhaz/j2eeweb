package org.obarcia.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.obarcia.demo.exceptions.ArticleNotFoundException;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleManager;
import org.obarcia.demo.models.ListPagination;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.user.ForgotForm;
import org.obarcia.demo.models.user.RegisterForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * Web controller.
 * 
 * @author obarcia
 */
// FIX: Página de error: Mejoras gráficas (Textos)
// FIX: Revisar los managers, los session.close y no poder leer y guardar una entidad
// FIX: Validators: register, forgot
// TODO: !!!! Añadir comentarios
// TODO: !!!! Usuario: Registro
// TODO: !!!! Usuario: Forgot password
// TODO: !!!! Repositorio de imágenes
// TODO: !!!! Listado de comentarios: estilos de cada comentario
// TODO: Administración: Index
// TODO: Administración: Users
// TODO: Administración: Articles
// TODO: Administración: Articles: Comments
// TODO: Administración: Tinymce
// TODO: Buscador por texto
// TODO: Breadcrumb en article
// TODO: Obtener los artículos más vistos / comentados.
// XXX: Security: Extra parameters
// XXX: Header: Logo
// XXX: Header: Buscador
// XXX: Footer: estilos y texto final
// XXX: Header: Secciones: Estilos over y active
// XXX: Botón de play para los videos
// XXX: Navegador de los artículos: Splash de refresco
// XXX: Navegador de los artículos: Reposicionar la página
// XXX: Crear datos de demo incialmente
// XXX: Varios idiomas

@Controller
@RequestMapping("/")
public class WebController
{
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
        // Listado principal
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10, "all", tag);
        
        // Otros listados
        List importants = ArticleManager.getInstance().getArticlesImportants(tag);
        List guides = ArticleManager.getInstance().getArticlesGuides(tag);
        List reviews = ArticleManager.getInstance().getArticlesReviews(tag);
        List moreComments = ArticleManager.getInstance().getArticlesMoreComments(tag);
        
        return new ModelAndView("articles/articles")
                .addObject("tag", tag)
                .addObject("importants", importants)
                .addObject("articles", articles)
                .addObject("guides", guides)
                .addObject("reviews", reviews)
                .addObject("moreComments", moreComments);
    }
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id) throws ArticleNotFoundException
    {
        Comment comment = new Comment();
        Article model = ArticleManager.getInstance().getArticle(id);
        if (model != null) {
            return new ModelAndView("articles/article")
                    .addObject("comment", comment)
                    .addObject("model", model);
        } else {
            throw new ArticleNotFoundException();
        }
    }
    // ****************************************
    // AJAX
    // ****************************************
    @GetMapping("/ajax/comments/{id}")
    public ModelAndView actionCommentsTag(
            @PathVariable("id") int id)
    {
        ListPagination comments = ArticleManager.getInstance().getComments(id, 1, 10);
        
        return new ModelAndView("articles/comments.ajax")
                .addObject("id", id)
                .addObject("comments", comments);
    }
    @GetMapping("/ajax/comments/{id}/{page}")
    public ModelAndView actionCommentsTag(
            @PathVariable("id") int id, 
            @PathVariable("page") int page)
    {
        ListPagination comments = ArticleManager.getInstance().getComments(id, page, 10);
        
        return new ModelAndView("articles/comments.ajax")
                .addObject("id", id)
                .addObject("comments", comments);
    }
    @GetMapping("/ajax/{tag}/{type}")
    public ModelAndView actionArticlesTag(
            @PathVariable("tag") String tag, 
            @PathVariable("type") String type)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10, type, tag);
        
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag", tag)
                .addObject("articles", articles);
    }
    @GetMapping("/ajax/{tag}/{type}/{page}")
    public ModelAndView actionArticlesTag(
            @PathVariable("tag") String tag, 
            @PathVariable("type") String type, 
            @PathVariable("page") int page)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(page, 10, type, tag);
        
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag", tag)
                .addObject("articles", articles);
    }
    // ****************************************
    // USER
    // ****************************************
    @RequestMapping("/user/login")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionLogin(
            @RequestParam(value = "error", required = false) String error)
    {
        return new ModelAndView("user/login")
            .addObject("error", error);
    }
    @GetMapping("/user/logout")
    @PreAuthorize("isAuthenticated()")
    public String actionLogout(
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @RequestMapping("/user/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionRegister(
            @Valid @ModelAttribute("model") RegisterForm form,
            BindingResult result)
    {
        return new ModelAndView("user/register")
            .addObject("model", form);
    }
    @RequestMapping("/user/forgot")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionForgotPassword(
            @Valid @ModelAttribute("model") ForgotForm form,
            BindingResult result)
    {
        return new ModelAndView("user/forgot")
            .addObject("model", form);
    }
    @RequestMapping("/user/profile")
    @PreAuthorize("isAuthenticated()")
    public String actionProfile()
    {
        return "user/profile";
    }
}