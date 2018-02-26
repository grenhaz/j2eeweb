package org.obarcia.demo.controllers;

import javax.validation.Valid;
import org.obarcia.demo.models.contact.ContactForm;
import org.obarcia.demo.models.user.LoginForm;
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
// TODO: Página de error
// TODO: I18n: Completar los JSP
// TODO: Obtener la sección actual para marcarla en el layout
// TODO: FOOTER
// TODO: Security
// TODO: Section: Home
// TODO: Section: Blog
// TODO: Section: Contact
// TODO: Section: Login
// TODO: Login y logout (Control del usuario)
// TODO: Validators
// TODO: Handle errors
@Controller
@RequestMapping("/")
public class WebController {
    @GetMapping("/")
    public ModelAndView actionIndex()
    {
        return new ModelAndView("index");
    }
    @RequestMapping("/login")
    public ModelAndView actionLogin()
    {
        // TODO: Si está logueado se redirecciona al index
        LoginForm model = new LoginForm();
        return new ModelAndView("user/login", "model", model);
    }
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