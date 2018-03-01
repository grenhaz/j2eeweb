package org.obarcia.demo.controllers;

import java.util.Date;
import org.obarcia.demo.components.hibernate.HibernateConnector;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.Comment;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author obarcia
 */
@Controller
@RequestMapping("/demo")
public class DemoController
{
    @GetMapping("/create")
    public String createDemo()
    {
        // Usuarios
        createUser("admin@test.com", "password", "ROLE_ADMIN");
        createUser("user@test.com", "password", "ROLE_USER");
        // Artículos
        createArticle("new", "Prueba", "Description", "Content", "[PC][PS4]", null, true, 12);
        
        return "redirect:/";
    }
    private Article createArticle(
        String type, String title, 
        String description, String content,
        String tags, Double puntuation,
        boolean important, int comments)
    {
        Article article = new Article();
        article.setType(type);
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setTags(tags);
        article.setScore(puntuation);
        article.setImportant(important);
        article.setPublish(new Date());
        if (!HibernateConnector.getInstance().save(article)) {
            throw new RuntimeException("Article creation error.");
        }
        
        User user = UserManager.getInstance().getUserByName("user@test.com");
        for (int i = 0; i < comments; i ++) {
            Comment c = new Comment();
            c.setArticle(article);
            c.setUser(user);
            c.setContent("Hello World!!!!");
            c.setPublish(new Date());
            HibernateConnector.getInstance().save(c);
        }
        
        return article;
    }
    private User createUser(String username, String password, String userRole)
    {
        User search = UserManager.getInstance().getUserByName(username);
        User user = new User();
        if (search != null) {
            user.setId(search.getId());
        }
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setUserRole(userRole);
        user.setActive(Boolean.TRUE);
        if (!HibernateConnector.getInstance().save(user)) {
            throw new RuntimeException("User creation error.");
        }
        
        return user;
    }
}