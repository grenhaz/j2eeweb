package org.obarcia.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleManager;
import org.obarcia.demo.models.ListPagination;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web controller.
 * 
 * @author obarcia
 */
// FIX: !!!! Añadir comentarios
// FIX: Página de error: Códigos de error y fondo (Parece que da un error)
// FIX: I18n: Completar los JSP (Falta el NAVBAR)
// TODO: !!!! Utilizar un fichero de configuración: url's
// TODO: !!!! Security: Acceso por usuario de BBDD.
// TODO: Security: Extra parameters
// TODO: Administración: Index
// TODO: Administración: Users
// TODO: Administración: Articles
// TODO: Administración: Articles: Comments
// TODO: BBDD (Creación durante el inicio)
// TODO: Navbar completa
// TODO: Login: Registro y página completa
// TODO: Página del artículo
// TODO: !!!! Comments
// TODO: Footer: estilos y texto final
// TODO: Seciones: Estilos over y active
// TODO: Botón de play para los videos
// TODO: Validators
// TODO: Handle errors
// TODO: Buscador por texto
// TODO: Navegador de los artículos (Falta el splash de refresco)
// TODO: !!!! Repositorio de imágenes
// TODO: Breadcrumb en article
// TODO: Menú superior (HOME, TAGS??)
// TODO: !!!! Revisar el pagination
// TODO: !!!! Revisar el uso de base para las url's y cambiar por c:url
// FIX: !!!! Corregir la colocación del ul.list-sections .active

@Controller
@RequestMapping("/")
public class WebController
{
    @GetMapping("/")
    public ModelAndView actionIndex(HttpServletRequest request)
    {
        // Listado principal
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10);
        articles.setUrlBase(request.getContextPath());
        
        // Otros listados
        List importants = ArticleManager.getInstance().getArticlesImportants();
        List guides = ArticleManager.getInstance().getArticlesGuides();
        List reviews = ArticleManager.getInstance().getArticlesReviews();
        
        return new ModelAndView("articles/articles")
                .addObject("tag", "games")
                .addObject("importants", importants)
                .addObject("articles", articles)
                .addObject("guides", guides)
                .addObject("reviews", reviews);
    }
    @GetMapping("/web/{tag}")
    public ModelAndView actionIndexTag(@PathVariable("tag") String tag, HttpServletRequest request)
    {
        // Listado principal
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10, "all", tag);
        articles.setUrlBase(request.getContextPath());
        
        // Otros listados
        List importants = ArticleManager.getInstance().getArticlesImportants(tag);
        List guides = ArticleManager.getInstance().getArticlesGuides(tag);
        List reviews = ArticleManager.getInstance().getArticlesReviews(tag);
        
        return new ModelAndView("articles/articles")
                .addObject("tag", tag)
                .addObject("importants", importants)
                .addObject("articles", articles)
                .addObject("guides", guides)
                .addObject("reviews", reviews);
    }
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(@PathVariable("id") int id)
    {
        Article model = ArticleManager.getInstance().getArticle(id);
        return new ModelAndView("articles/article")
                .addObject("model", model);
    }
    @GetMapping("/ajax/{tag}/{type}")
    public ModelAndView actionArticlesTag(@PathVariable("tag") String tag, @PathVariable("type") String type, HttpServletRequest request)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10, type, tag);
        articles.setUrlBase(request.getContextPath());
        
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag", tag)
                .addObject("articles", articles);
    }
    @GetMapping("/ajax/{tag}/article/{id}")
    public ModelAndView actionArticle(@PathVariable("tag") String tag, @PathVariable("id") int id)
    {
        Article model = ArticleManager.getInstance().getArticle(id);
        return new ModelAndView("articles/article")
                .addObject("tag", tag)
                .addObject("model", model);
    }
    @GetMapping("/ajax/{tag}/{type}/{page}")
    public ModelAndView actionArticlesTag(@PathVariable("tag") String tag, @PathVariable("type") String type, @PathVariable("page") int page, HttpServletRequest request)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(page, 10, type, tag);
        articles.setUrlBase(request.getContextPath());
        
        return new ModelAndView("articles/articles.ajax")
                .addObject("tag", tag)
                .addObject("articles", articles);
    }
    
    
    
    
    
    @RequestMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public String actionLogin()
    {
        return "login";
    }
    @GetMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String actionLogout (HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    
    /*@GetMapping("/contact")
    public ModelAndView actionContact()
    {
        ContactForm model = new ContactForm();
        return new ModelAndView("section/contact/contact", "model", model);
    }
    @PostMapping("/contact")
    public ModelAndView actionContactSubmit(@Valid @ModelAttribute("model") ContactForm model, BindingResult result)
    {
        if (!result.hasErrors()) {
            // TODO: Save
        }
        
        // TODO: Redirect
        return new ModelAndView("section/contact/success", "model", model);
    }
    @GetMapping("/blog")
    public ModelAndView actionBlog()
    {
        List posts = HibernateConnector.getInstance().getAll(Article.class);
        return new ModelAndView("section/blog/blog", "posts", posts);
    }
    @GetMapping("/blog/{id}")
    public ModelAndView actionBlogPost(@PathVariable("id") int id)
    {
        Comment comment = new Comment();
        Article post = (Article)HibernateConnector.getInstance().get(Article.class, id);
        return new ModelAndView("section/blog/post")
                .addObject("model", post)
                .addObject("comment", comment);
    }
    @PostMapping("/blog/{id}")
    public ModelAndView actionBlogSubmit(@PathVariable("id") int id, @Valid @ModelAttribute("comment") CommentForm comment, BindingResult result)
    {
        Article post = (Article)HibernateConnector.getInstance().get(Article.class, id);
        
        if (post != null) {
            if (!result.hasErrors()) {
                // TODO: Save
                Comment c = new Comment();
                // TODO: Incluir el id del usuario
                c.setIdPost(post.getId());
                c.setContent(comment.getContent());
                if (!HibernateConnector.getInstance().save(c)) {
                    // TODO: Se produjo un error al guardar el comentario
                }
            }
        } else {
            // TODO: El POST no existe
        }
        
        // TODO: Redirect
        return new ModelAndView("section/blog/post")
            .addObject("model", post)
            .addObject("comment", comment);
    }*/
}