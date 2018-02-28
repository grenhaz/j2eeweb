package org.obarcia.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.obarcia.demo.models.Article;
import org.obarcia.demo.models.ArticleManager;
import org.obarcia.demo.models.ListPagination;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web controller.
 * 
 * @author obarcia
 */
// TODO: Añadir comentarios
// TODO: Página de error: Código y fondo
// TODO: I18n: Completar los JSP
// TODO: Obtener la sección actual para marcarla en el layout
// TODO: Security: Extra parameters
// TODO: Administración: Index
// TODO: Administración: Articles
// TODO: Administración: Comments
// TODO: BBDD
// TODO: Article: note, shortcontent, type (texto real)
// TODO: Navbar completa
// TODO: Login: Registro y página completa
// TODO: Página del artículo
// TODO: Comments
// TODO: Footer: estilos y texto final
// FIX: Validators
// TODO: Handle errors
// XXX: Ajax en J2EE
// TODO: Buscador por texto
// TODO: Navegador de los artículos
@Controller
@RequestMapping("/")
public class WebController {
    @GetMapping("/")
    public ModelAndView actionIndex(HttpServletRequest request)
    {
        // Listado principal
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10);
        articles.setType("all");
        articles.setUrlBase(request.getContextPath() + "/articles/" + articles.getType());
        
        // Otros listados
        List importants = ArticleManager.getInstance().getArticlesImportants();
        List guides = ArticleManager.getInstance().getArticlesGuides();
        List reviews = ArticleManager.getInstance().getArticlesReviews();
        
        return new ModelAndView("articles")
                .addObject("importants", importants)
                .addObject("articles", articles)
                .addObject("guides", guides)
                .addObject("reviews", reviews);
    }
    @GetMapping("/articles/{type}")
    public ModelAndView actionArticles(@PathVariable("type") String type, HttpServletRequest request)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(1, 10, type);
        articles.setType(type != null ? type : "all");
        articles.setUrlBase(request.getContextPath() + "/articles/" + articles.getType());
        
        return new ModelAndView("articles.ajax")
                .addObject("articles", articles);
    }
    @GetMapping("/articles/{type}/{page}")
    public ModelAndView actionArticles(@PathVariable("type") String type, @PathVariable("page") int page, HttpServletRequest request)
    {
        ListPagination articles = ArticleManager.getInstance().getArticlesAll(page, 10, type);
        articles.setType(type != null ? type : "all");
        articles.setUrlBase(request.getContextPath() + "/articles/" + articles.getType());
        
        return new ModelAndView("articles.ajax")
                .addObject("articles", articles);
    }
    @GetMapping("/article/{id}")
    public ModelAndView actionBlogPost(@PathVariable("id") int id)
    {
        Article model = ArticleManager.getInstance().getArticle(id);
        return new ModelAndView("article")
                .addObject("model", model);
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