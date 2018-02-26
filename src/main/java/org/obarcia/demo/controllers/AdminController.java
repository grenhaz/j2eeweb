package org.obarcia.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Heck
 */
@Controller
@RequestMapping("/admin")
public class AdminController
{
    /**
     * LOGIN
     */
    @RequestMapping("/index")
    public String actionAdmin()
    {
        return "admin/admin";
    }
    /**
     * LOGIN
     */
    @RequestMapping("/login")
    public String actionLogin()
    {
        return "admin/login";
    }
    /**
     * LOGOUT
     */
    @GetMapping("/logout")
    public String actionLogout (HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
