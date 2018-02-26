package org.obarcia.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.obarcia.demo.models.contact.ContactForm;
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
// TODO: FOOTER: Redes sociales y texto
// TODO: Security: Extra parameters
// TODO: Section: Home (Completar)
// TODO: Section: Blog (Listado de POST's)
// FIX: Section: Contact: No valida
// TODO: Administración: Index
// TODO: Administración: Post
// TODO: BBDD
// FIX: Validators
// TODO: Handle errors
// XXX: Barra de carga al cambiar de sección
// XXX: Ajax en J2EE
@Controller
@RequestMapping("/")
public class WebController {
    /**
     * INDEX 
     */
    @GetMapping("/")
    public ModelAndView actionIndex()
    {
        return new ModelAndView("index");
    }
    /**
     * CONTACT 
     */
    @GetMapping("/contact")
    public ModelAndView actionContact()
    {
        ContactForm model = new ContactForm();
        return new ModelAndView("section/contact/contact", "model", model);
    }
    @PostMapping("/contact")
    public ModelAndView actionContactSubmit(@Valid @ModelAttribute("model")ContactForm model, BindingResult result) {
        if (!result.hasErrors()) {
            // TODO: Save
        }
        
        // TODO: Redirect
        return new ModelAndView("section/contact/success", "model", model);
    }
    /**
     * BLOG
     */
    @GetMapping("/blog")
    public ModelAndView actionBlog()
    {
        return new ModelAndView("section/blog/blog");
    }
    @GetMapping("/blog/{id}")
    public ModelAndView actionBlogPost(@PathVariable("id") int id)
    {
        return new ModelAndView("section/blog/post");
    }
}