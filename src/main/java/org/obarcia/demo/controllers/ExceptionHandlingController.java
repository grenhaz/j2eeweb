package org.obarcia.demo.controllers;

import org.obarcia.demo.exceptions.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Error controller.
 * 
 * @author obarcia
 */
@ControllerAdvice
public class ExceptionHandlingController
{
    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView actionArticleNotFound(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.article_not_found")
            .addObject("exception", ex);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView actionNoHandler(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.404")
            .addObject("exception", ex);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView actionAccessDenied(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.404")
            .addObject("exception", ex);
    }
    // TODO: Reactivar para producción
    /*@ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView actionInternalError(Exception ex)
    {
        return new ModelAndView("error")
            .addObject("message", "error.505")
            .addObject("exception", ex);
    }*/
}