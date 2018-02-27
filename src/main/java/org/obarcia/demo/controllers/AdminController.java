package org.obarcia.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Heck
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
}
