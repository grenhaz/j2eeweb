package org.obarcia.demo.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.components.mail.MailSenderImpl;
import org.obarcia.demo.exceptions.PageNotFoundException;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.models.user.AccountDetails;
import org.obarcia.demo.models.user.ForgotForm;
import org.obarcia.demo.models.user.PasswordForm;
import org.obarcia.demo.models.user.ProfileForm;
import org.obarcia.demo.models.user.RegisterForm;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.services.ArticleService;
import org.obarcia.demo.services.UserAccessService;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para el Usuario.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    /**
     * Instancia del Contexto del servlet.
     */
    @Autowired
    ServletContext servletContext;
    /**
     * Instancia del manager de autentificación
     */
    //@Autowired
    //private AuthenticationManager authenticationManager;
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
     * Instancia del servicio para el envío de emails.
     */
    @Autowired
    private MailSenderImpl mailSender;
    @Autowired
    private UserAccessService userDetailsService;
    
    /**
     * Proceso de login.
     * @param error true si se produjo algún error durante el login.
     * @return Vista resultante.
     */
    @RequestMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionLogin(
            @RequestParam(value = "error", required = false) String error)
    {
        return new ModelAndView("user/login")
            .addObject("error", error);
    }
    /**
     * Proceso de logout.
     * @param request Intancia de la petición
     * @param response Instancia de la respuesta.
     * @return Vista resultante.
     */
    @GetMapping("/logout")
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
    /**
     * Formulario de registro.
     * @return Vista resultante.
     */
    @GetMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionRegister()
    {
        return new ModelAndView("user/register")
            .addObject("model", new RegisterForm());
    }
    /**
     * Procesamiento del formulario de registro.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @return Vista resultante.
     * @throws SaveException 
     */
    @PostMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionRegister(
            @Valid @ModelAttribute("model") RegisterForm form,
            BindingResult result) throws SaveException
    {
        if (!result.hasErrors()) {
            User newUser = new User();
            newUser.setNickname(form.getNickname());
            newUser.setEmail(form.getEmail());
            newUser.setPassword(new BCryptPasswordEncoder().encode(Utilities.getRandomHexString(8)));
            newUser.setCreated(new Date());
            newUser.setUserRole(User.ROLE_USER);
            newUser.setActive(Boolean.FALSE);
            newUser.setUkey(Utilities.getRandomHexString(64));
            if (userService.save(newUser)) {
                // TODO: OFF: Crear la vista del email
                // Enviar el mail de recuperación
                SimpleMailMessage emailObj = new SimpleMailMessage();
                emailObj.setTo(form.getEmail());
                emailObj.setSubject("EWQ");
                emailObj.setText("QWE");
                mailSender.send(emailObj);

                // TODO; Redirect with message
            } else {
                throw new SaveException();
            }
        }
        
        return new ModelAndView("user/register")
            .addObject("model", form);
    }
    /**
     * Proceso de activación de un usuario tras registrarse.
     * @param ukey Clave del usuario.
     * @param locale Localización para el i18n
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws PageNotFoundException
     */
    @GetMapping("/activate")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionActivateAccount(
        @RequestParam(value = "k", required = true) String ukey,
        Locale locale,
        RedirectAttributes flash) throws PageNotFoundException
    {
        // Buscar el usuario por la clave (No debe estar activado ya)
        User user = userService.getUserByUkey(ukey);
        if (user != null) {
            // Activar y borrar el ukey
            user.setActive(Boolean.TRUE);
            user.setUkey("");
            if (userService.save(user)) {
                // TODO: OFF: Auto loguear al usuario
                /*UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
                authenticationManager.authenticate(auth);
                if (auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }*/
                
                // Añadir mensaje flash (I18N)
                flash.addFlashAttribute("flash", messageSource.getMessage("message.user.activate.ok", null, locale));
                
                // Redireccionar con mensaje
                return new ModelAndView("redirect:/");
            }
        }
        
        throw new PageNotFoundException();
    }
    /**
     * Recuperar la contraseña de un usuario.
     * @param ukey Clave del usuario.
     * @param password Contraseña nueva.
     * @param locale Localización para el i18n.
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws PageNotFoundException
     */
    @GetMapping("/recover")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionRecoverAccount(
        @RequestParam(value = "k", required = true) String ukey,
        @RequestParam(value = "p", required = true) String password,
        Locale locale,
        RedirectAttributes flash) throws PageNotFoundException
    {
        // Buscar el usuario por la clave (No debe estar activado ya)
        User user = userService.getUserByUkey(ukey);
        if (user != null) {
            // Activar y borrar el ukey
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setUkey("");
            if (userService.save(user)) {
                // TODO: OFF: Auto loguear al usuario
                /*UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
                authenticationManager.authenticate(auth);
                if (auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }*/
                
                // Añadir mensaje flash (I18N)
                flash.addFlashAttribute("flash", messageSource.getMessage("message.user.recovery.ok", null, locale));
                
                // Redireccionar con mensaje
                return new ModelAndView("redirect:/");
            }
        }
        
        throw new PageNotFoundException();
    }
    /**
     * Formulario de recuperación de contraseña.
     * @return Vista resultante.
     */
    @GetMapping("/forgot")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionForgotPassword()
    {
        return new ModelAndView("user/forgot")
            .addObject("model", new ForgotForm());
    }
    /**
     * Procesamiento del formulario de recuperación de contraseña.
     * @param form Instancia del formulario.
     * @param result Rsultado de la validación.
     * @param locale Localización para el i18n
     * @param flash Flash variables.
     * @return Vista resultante.
     */
    @PostMapping("/forgot")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionForgotPassword(
            @Valid @ModelAttribute("model") ForgotForm form,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash)
    {
        if (!result.hasErrors()) {
            User user = userService.getUserByEmail(form.getEmail());
            if (user != null) {
                // Guardar la clave para la recuperación
                user.setUkey(Utilities.getRandomHexString(64));
                if (userService.save(user)) {
                    // TODO: OFF: Crear la vista del email
                    // Enviar el mail de recuperación
                    SimpleMailMessage emailObj = new SimpleMailMessage();
                    emailObj.setTo(form.getEmail());
                    emailObj.setSubject("EWQ");
                    emailObj.setText("QWE");
                    mailSender.send(emailObj);
                    
                    // Añadir mensaje flash (I18N)
                    flash.addFlashAttribute("flash", messageSource.getMessage("message.user.forgot.ok", null, locale));
                    
                    // Redireccionar
                    return new ModelAndView("redirect:/");
                } else {
                    result.rejectValue("email", "error.sendmail", "Se produjo un error durante el envío del email.");
                }
            } else {
                result.rejectValue("email", "error.recovery.email", "El usuario no existe.");
            }
        }
        
        return new ModelAndView("user/forgot")
            .addObject("model", form);
    }
    /**
     * Perfil del usuario.
     * @return Vista resultante.
     */
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionProfile()
    {
        ProfileForm form = getProfileForm();
        PasswordForm pform = new PasswordForm();
        pform.setId(form.getId());
        
        return new ModelAndView("user/profile")
            .addObject("form", form)
            .addObject("pform", pform)
            .addObject("comments", articleService.getLastCommentsByUser(form.getId(), 8));
    }
    /**
     * Genera y devuelve el ProfileForm.
     * @return Instancia del ProfileForm.
     */
    private ProfileForm getProfileForm()
    {
        ProfileForm form = new ProfileForm();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            AccountDetails account = ((AccountDetails)auth.getPrincipal());
            form.setId(account.getId());
            form.setNickname(account.getNickname());
            form.setAvatar(account.getAvatar());
        }
        
        return form;
    }
    /**
     * Procesamiento del formulario de cambio de perfil.
     * @param form Instancia del formulario.
     * @param result Resultado de la validación.
     * @return Vista resultante.
     * @throws SaveException 
     */
    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionProfileInfo(
            @Valid @ModelAttribute("model") ProfileForm form,
            BindingResult result) throws SaveException
    {
        if (!result.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                AccountDetails account = ((AccountDetails)auth.getPrincipal());
                if (account.getId().equals(form.getId())) {
                    account.setNickname(form.getNickname());
                    account.setAvatar(form.getAvatar());
                    User user = userService.getUserById(account.getId());
                    if (user != null) {
                        user.setNickname(form.getNickname());
                        user.setAvatar(form.getAvatar());
                        if (userService.save(user)) {
                            // Redirect
                            return new ModelAndView("redirect:/user/profile");
                        }
                    }
                }
            }
            
            throw new SaveException();
        }
        
        PasswordForm pform = new PasswordForm();
        pform.setId(form.getId());
        
        return new ModelAndView("user/profile")
            .addObject("form", form)
            .addObject("pform", pform)
            .addObject("comments", articleService.getLastCommentsByUser(form.getId(), 8));
    }
    /**
     * Procesamiento del formulario de cambio de contraseña del usuario.
     * @param pform Instancia del formulario.
     * @param result Resultado de la validación.
     * @param locale Localización para el i18n
     * @param flash Flash variables.
     * @return Vista resultante.
     * @throws SaveException 
     */
    @PostMapping("/profile/password")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionProfilePassword(
            @Valid @ModelAttribute("model") PasswordForm pform,
            BindingResult result,
            Locale locale,
            RedirectAttributes flash) throws SaveException
    {
        // TODO: OFF: Usuario: Perfil (Cambiar contraseña) => TEST
        if (!result.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                AccountDetails account = ((AccountDetails)auth.getPrincipal());
                if (account.getId().equals(pform.getId())) {
                    User user = userService.getUserById(account.getId());
                    if (user != null) {
                        user.setPassword(new BCryptPasswordEncoder().encode(pform.getPassword()));
                        if (userService.save(user)) {
                            // Añadir mensaje flash (I18N)
                            flash.addFlashAttribute("flash", messageSource.getMessage("message.profile.password.ok", null, locale));
                            
                            // Redirect
                            return new ModelAndView("redirect:/user/profile");
                        }
                    }
                }
            }
            
            throw new SaveException();
        }
        
        ProfileForm form = getProfileForm();
        
        return new ModelAndView("user/profile")
            .addObject("form", form)
            .addObject("pform", pform)
            .addObject("comments", articleService.getLastCommentsByUser(form.getId(), 8));
    }
    /**
     * Listado de avatares para selección en le formulario de cambio de perfil.
     * @param field Campo donde asignar el nuevo avatar.
     * @return Vista resultante.
     */
    @GetMapping("/avatars")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionAvatarAjax(
            @RequestParam(value = "field", required = true) String field)
    {
        List<String> avatars = new ArrayList<>();
        avatars.add("anonymous.png");
        
        // Leer los posibles avatares => NO SALE NINGUNO, revisar path
        File rootDir = new File( servletContext.getRealPath("/WEB-INF/data/avatars/") );
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            for (File f: files) {
                if (f.isFile() && !f.getName().equals("anonymous.png")) {
                    avatars.add(f.getName());
                }
            }
        }
        
        return new ModelAndView("user/avatars")
            .addObject("field", field)
            .addObject("avatars", avatars);
    }
}