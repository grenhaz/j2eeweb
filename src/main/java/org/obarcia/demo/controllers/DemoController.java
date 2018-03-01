package org.obarcia.demo.controllers;

import java.util.Date;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleManager;
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
        createUser("administrador", "admin@test.com", "password", "ROLE_ADMIN");
        createUser("obarcia", "user@test.com", "password", "ROLE_USER");
        // Artículos
        createArticle("new", "Mañana es el cierre de servidores de Demon's Souls", "El clásico de PlayStation 3 cortará a partir de mañana sus posibilidades on-line definitivamente.", "Content", "[PC]", null, true, 3);
        createArticle("new", "El rodaje de la serie televisiva de Halo comenzaría a finales de 2018", "El afamado director Steven Spielberg continuaría vinculado al proyecto.", "Content", "[PC][PS4]", null, true, 5);
        createArticle("guide", "Guía de Mario Kart 8 Deluxe", "", "Content", "[SWITCH]", null, true, 1);
        createArticle("video", "Turok Remastered", "Turok y Turok 2 tendrán remasterización en Xbox One el 2 de marzo", "Content", "[PS4]", null, true, 0);
        createArticle("review", "Análisis de Into the Breach", "Estrategia implacable de los creadores de FTL", "Content", "[PC][PS4]", 8.5, true, 3);
        createArticle("new", "¿Filtrado Plants vs. Zombies: Garden Warfare 3? Un cómic lo desvela", "El tebeo narrará los acontecimientos entre la segunda y la tercera parte del juego.", "Content", "[PC][PS4]", null, true, 3);
        createArticle("video", "Devil May Cry HD Collection llega a la nueva generación. Tráiler", "Capcom estrena el 13 de marzo Devil May Cry HD Collection en PS4 y Xbox One. Supone traer de vuelta a los tiempos actuales", "Content", "[PS4]", null, true, 6);
        createArticle("new", "GT Sport se actualiza y suma nuevos coches y dos nuevos circuitos", "La actualización incluye doce coches y tres eventos de la GT League.", "Content", "[PS4]", null, true, 12);
        createArticle("new", "Las ventas de PS4 en Japón mejoran todos los años desde su estreno", "Cerró su cuarto año en el mercado nipón con 2.083.974 unidades vendidas.", "Content", "[PS4]", null, true, 32);
        createArticle("new", "Habrá cambios en la cúpula directiva de Sony Interactive Entertainment", "Se efectuarán a partir del próximo 1 de abril y están implicados distintos empresarios.", "Content", "[PS4]", null, true, 9);
        createArticle("special", "Reportaje: Juegos olvidados", "", "Content", "[PC]", null, true, 4);
        //createArticle("new", "qwe", "qwe", "Content", "[PC]", null, true, 3);
        
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
        if (!ArticleManager.getInstance().save(article)) {
            throw new RuntimeException("Article creation error.");
        }
        
        User user = UserManager.getInstance().getUserByName("user@test.com");
        for (int i = 0; i < comments; i ++) {
            Comment c = new Comment();
            c.setArticle(article);
            c.setUser(user);
            c.setContent("Hello World!!!!");
            c.setPublish(new Date());
            ArticleManager.getInstance().save(c);
        }
        
        return article;
    }
    private User createUser(String name, String username, String password, String userRole)
    {
        User search = UserManager.getInstance().getUserByName(username);
        User user = new User();
        if (search != null) {
            user.setId(search.getId());
        }
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setName(name);
        user.setUserRole(userRole);
        user.setActive(Boolean.TRUE);
        if (!UserManager.getInstance().save(user)) {
            throw new RuntimeException("User creation error.");
        }
        
        return user;
    }
}