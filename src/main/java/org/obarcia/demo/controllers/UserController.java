package org.obarcia.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.components.mail.MailSenderImpl;
import org.obarcia.demo.exceptions.SaveException;
import org.obarcia.demo.models.user.AccountDetails;
import org.obarcia.demo.models.user.ForgotForm;
import org.obarcia.demo.models.user.ProfileForm;
import org.obarcia.demo.models.user.RegisterForm;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

// TODO: Usuario: Cambiar contraseña por URL
// TODO: Usuario: Perfil (Cambiar contraseña)
/**
 * Controller para el Usuario.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private MailSenderImpl mailSender;
    
    // ****************************************
    // USER
    // ****************************************
    @RequestMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionLogin(
            @RequestParam(value = "error", required = false) String error)
    {
        return new ModelAndView("user/login")
            .addObject("error", error);
    }
    @GetMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String actionLogout(
            @RequestParam(value = "u", required = false) String urlBack,
            HttpServletRequest request, 
            HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/" + (urlBack != null ? urlBack : "");
    }
    @GetMapping("/register")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionRegister()
    {
        return new ModelAndView("user/register")
            .addObject("model", new RegisterForm());
    }
    @GetMapping("/activate")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionActivateAccount(
        @RequestParam(value = "k", required = true) String ukey)
    {
        // TODO: Off: Buscar el usuario por la clave (No debe estar activado ya)
        // TODO: Off: Activar y borrar el ukey
        // TODO: Off: Auto loguear al usuario???
        return new ModelAndView("user/register")
            .addObject("model", new RegisterForm());
    }
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
                // TODO: Crear la vista del email
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
    @GetMapping("/forgot")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionForgotPassword()
    {
        return new ModelAndView("user/forgot")
            .addObject("model", new ForgotForm());
    }
    @PostMapping("/forgot")
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView actionForgotPassword(
            @Valid @ModelAttribute("model") ForgotForm form,
            BindingResult result)
    {
        if (!result.hasErrors()) {
            User user = userService.getUserByEmail(form.getEmail());
            if (user != null) {
                // Guardar la clave para la recuperación
                user.setUkey(Utilities.getRandomHexString(64));
                if (userService.save(user)) {
                    // TODO: Crear la vista del email
                    // Enviar el mail de recuperación
                    SimpleMailMessage emailObj = new SimpleMailMessage();
                    emailObj.setTo(form.getEmail());
                    emailObj.setSubject("EWQ");
                    emailObj.setText("QWE");
                    mailSender.send(emailObj);
                    
                    // TODO; Redirect with message
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
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionProfile()
    {
        ProfileForm form = new ProfileForm();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            AccountDetails account = ((AccountDetails)auth.getPrincipal());
            form.setId(account.getId());
            form.setNickname(account.getNickname());
            form.setAvatar(account.getAvatar());
        }
        
        return new ModelAndView("user/profile")
            .addObject("model", form);
    }
    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionProfile(
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
                            return new ModelAndView("redirect:/user/profile");
                        }
                    }
                }
            }
            
            throw new SaveException();
        }
        
        return new ModelAndView("user/profile")
            .addObject("model", form);
    }
    @GetMapping("/avatars")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView actionAvatarAjax(
            @RequestParam(value = "field", required = true) String field)
    {
        List<String> avatars = new ArrayList<>();
        avatars.add("anonymous.png");
        // TODO: Leer los posibles avatares
        avatars.add("avatar1.jpg");
        avatars.add("avatar2.jpg");
        avatars.add("avatar3.jpg");
        
        return new ModelAndView("user/avatars")
            .addObject("field", field)
            .addObject("avatars", avatars);
    }
}
