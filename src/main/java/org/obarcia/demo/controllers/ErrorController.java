package org.obarcia.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Error controller.
 * 
 * @author obarcia
 */
@Controller
@RequestMapping("/error")
public class ErrorController
{
    @RequestMapping("/{code}")
    public ModelAndView getError(@PathVariable("code") String code)
    {
        return new ModelAndView("error", "code", code);
    }
}
