package org.obarcia.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.user.UserLite;
import org.obarcia.demo.services.ArticleService;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO: Administración: Index (Estadísticas: Artículos / Comentarios / Ultimos comentarios / Mas comentado)
// TODO: Administración: Formularios
// TODO: Administración: Tinymce
// TODO: !!!! Administración: Tablas de listados (FILTERS, ACTIVE, CUSTOM TEXTS)
/**
 * Controller para la Administración
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController
{
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private UserService userService;
    
    /**
     * LOGIN
     */
    @GetMapping("/index")
    public String actionIndex()
    {
        return "admin/index";
    }
    @GetMapping("/users")
    public String actionUsers()
    {
        return "admin/users";
    }
    @GetMapping("/ajax/users")
    public @ResponseBody DataTablesResponse<UserLite> actionUsersAjax(HttpServletRequest request)
    {
        return userService.getUsersLite(new DataTablesRequest(request));
    }
    @GetMapping("/articles")
    public String actionArticles()
    {
        return "admin/articles";
    }
    @GetMapping("/ajax/articles")
    public @ResponseBody DataTablesResponse<ArticleLite> actionArticlesAjax(HttpServletRequest request)
    {
        return articleService.getArticlesLite(new DataTablesRequest(request));
    }
}
