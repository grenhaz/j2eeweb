package org.obarcia.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.obarcia.demo.components.hibernate.HibernateConnector;
import org.obarcia.demo.models.blog.Comment;
import org.obarcia.demo.models.blog.CommentForm;
import org.obarcia.demo.models.blog.Post;
import org.obarcia.demo.models.contact.ContactForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web controller.
 * 
 * @author obarcia
 */
// TODO: Añadir comentarios
// TODO: Página de error: No mostrar secciones y formatear el error y el tipo de error
// TODO: I18n: Completar los JSP
// TODO: Obtener la sección actual para marcarla en el layout
// TODO: Security: Extra parameters
// TODO: Section: Home (Completar)
// TODO: Section: Blog (Listado de POST's)
// FIX: Section: Contact: No valida
// TODO: Administración: Index
// TODO: Administración: Post
// TODO: Administración: Comments
// TODO: BBDD
// TODO: Comments
// FIX: Validators
// TODO: Handle errors
// XXX: Ajax en J2EE
@Controller
@RequestMapping("/")
public class WebController {
    @GetMapping("/")
    public ModelAndView actionIndex()
    {
        return new ModelAndView("index");
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
    @GetMapping("/contact")
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
        List posts = HibernateConnector.getInstance().getAll(Post.class);
        return new ModelAndView("section/blog/blog", "posts", posts);
    }
    @GetMapping("/blog/{id}")
    public ModelAndView actionBlogPost(@PathVariable("id") int id)
    {
        Comment comment = new Comment();
        Post post = (Post)HibernateConnector.getInstance().get(Post.class, id);
        return new ModelAndView("section/blog/post")
                .addObject("model", post)
                .addObject("comment", comment);
    }
    @PostMapping("/blog/{id}")
    public ModelAndView actionBlogSubmit(@PathVariable("id") int id, @Valid @ModelAttribute("comment") CommentForm comment, BindingResult result)
    {
        Post post = (Post)HibernateConnector.getInstance().get(Post.class, id);
        
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
    }
}