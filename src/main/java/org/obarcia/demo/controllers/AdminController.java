package org.obarcia.demo.controllers;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.obarcia.demo.components.datatables.DataTablesResponse;
import org.obarcia.demo.components.datatables.DataTablesRequest;
import org.obarcia.demo.exceptions.ArticleNotFoundException;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.exceptions.UserNotFoundException;
import org.obarcia.demo.models.article.Article;
import org.obarcia.demo.models.article.ArticleLite;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.models.user.UserForm;
import org.obarcia.demo.models.user.UserLite;
import org.obarcia.demo.services.ArticleService;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// TODO: LOW: Administración: Activar / Desactivar usaurio y artículo.
// TODO: LOW: Administración: Usuario: Reenviar email de activación.
// TODO: LOW: Administración: Usuario: Enviar email de recuperación de cuenta.
// TODO: LOW: Administración: Estadísticas: Artículos, Comentarios, Mas comentado
// TODO: OFF: Administración: Formularios: Artículo: Completar y pruebas
// TODO: OFF: Administración: Formularios: Comentarios: Listado con datatables y  Eliminar / Deseliminar
// TODO: OFF: Administración: Formularios: Tinymce
// TODO: LOW: Administración: Tablas de listados FILTERS
// TODO: OFF: Administración: Tablas de listados ACTIVE / DESACTIVE
// TODO: OFF: Administración: Articulos: Columna type
/**
 * Controlador para la Administración.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController
{
    /**
     * Instancia del servicio i18n
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * Instancia del servicio de usuarios.
     */
    @Autowired
    private UserService userService;
    /**
     * Instancia del servicio de artículos.
     */
    @Autowired
    private ArticleService articleService;
    
    /**
     * Página principal.
     * @return Vista resultante.
     */
    @GetMapping("/index")
    public ModelAndView actionIndex()
    {
        return new ModelAndView("admin/index")
            .addObject("lastComments",  articleService.getLastComments(null, 10));
    }
    /**
     * Listado de usuarios.
     * @return Vista resultante.
     */
    @GetMapping("/users")
    public String actionUsers()
    {
        return "admin/users";
    }
    /**
     * Listado de usuarios para el DataTables.
     * @param request Instancia de la petición.
     * @return JSON resultante.
     */
    @GetMapping("/ajax/users")
    public @ResponseBody DataTablesResponse<UserLite> actionUsersAjax(HttpServletRequest request)
    {
        return userService.getUsersLite(new DataTablesRequest(request));
    }
    /**
     * Listado de artículos.
     * @return Vista resultante.
     */
    @GetMapping("/articles")
    public String actionArticles()
    {
        return "admin/articles";
    }
    /**
     * Listado de artículos para el DataTables.
     * @param request Instancia de la petición.
     * @return JSON resultante.
     */
    @GetMapping("/ajax/articles")
    public @ResponseBody DataTablesResponse<ArticleLite> actionArticlesAjax(HttpServletRequest request)
    {
        return articleService.getArticlesLite(new DataTablesRequest(request));
    }
    /**
     * Formulario de un usuario.
     * @param id Identificador del usuario.
     * @return Vista resultante.
     * @throws UserNotFoundException 
     */
    @GetMapping("/user/{id}")
    public ModelAndView actionUser(
            @PathVariable("id") int id) throws UserNotFoundException
    {
        // Buscar el usuario por id
        User user = userService.getUserById(id);
        if (user != null) {
            // Rellanar los datos del formulario con los del usuario
            UserForm form = new UserForm();
            form.setEmail(user.getEmail());
            form.setNickname(user.getNickname());
            
            // Vista del formulario
            return new ModelAndView("admin/user")
                    .addObject("id", id)
                    .addObject("form", form);
        } else {
            // No se encontró el usuario
            throw new UserNotFoundException();
        }
    }
    /**
     * Formulario de un usuario.
     * @param id Identificador del usuario.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @param locale Localización para el i18n.
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws SaveException 
     * @throws UserNotFoundException
     */
    @PostMapping("/user/{id}")
    public ModelAndView actionUser(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") UserForm form,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash) throws SaveException, UserNotFoundException
    {
        // Obtener el usuario por id
        User user = userService.getUserById(id);
        if (user != null) {
            // Si no hay errores
            if (!result.hasErrors()) {
                // Asignar los datos del formulario
                user.setEmail(form.getEmail());
                user.setNickname(form.getNickname());
                
                // Guardar el registro
                if (userService.save(user)) {
                    // Añadir mensaje flash (I18N)
                    flash.addFlashAttribute("flash", messageSource.getMessage("message.save.ok", null, locale));

                    // Redirect
                    return new ModelAndView("redirect:/admin/user/" + id);
                }

                throw new SaveException();
            }

            // Como hay errores se muestra la vista del formulario
            return new ModelAndView("admin/user")
                    .addObject("id", id)
                    .addObject("form", form);
        } else {
            // No se encontró el usuario
            throw new UserNotFoundException();
        }
    }
    /**
     * Formulario de un artículo.
     * @param id Identificador de lartículo.
     * @return Vista resultante.
     * @throws ArticleNotFoundException 
     */
    @GetMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id) throws ArticleNotFoundException
    {
        // Obtener el artículo por id
        Article form = articleService.getArticle(id);
        if (form != null) {
            // Vista con el formulario
            return new ModelAndView("admin/article")
                    .addObject("form", form);
        } else {
            // No se encontró el artículo
            throw new ArticleNotFoundException();
        }
    }
    /**
     * Formulario de un artículo.
     * @param id Identificador del artículo.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @param locale Localización para el i18n.
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws SaveException 
     */
    @PostMapping("/article/{id}")
    public ModelAndView actionArticle(
            @PathVariable("id") int id,
            @Valid @ModelAttribute("form") Article form,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash) throws SaveException
    {
        // El id del formulario y el de la url deben coincidir
        if (id == form.getId()) {
            // Si no hay errores
            if (!result.hasErrors()) {
                // Guardar el artículo
                if (articleService.save(form)) {
                    // Añadir mensaje flash (I18N)
                    flash.addFlashAttribute("flash", messageSource.getMessage("message.save.ok", null, locale));

                    // Redirect
                    return new ModelAndView("redirect:/admin/article/" + id);
                }

                // Error al guardar
                throw new SaveException();
            }

            // Si hubo errores se muestra el formulario
            return new ModelAndView("admin/article")
                        .addObject("form", form);
        } else {
            // No se encontró el artículo
            throw new SaveException();
        }
    }
}