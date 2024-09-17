package com.grupo3.app.config;

import com.grupo3.app.exception.EntityNotFoundBusinessException;
import com.grupo3.app.exception.NomeJaExisteException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionConfig {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundBusinessException.class)
    public ModelAndView handleNotFoundException(EntityNotFoundBusinessException exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("status", 404);
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NomeJaExisteException.class)
    public ResponseEntity<String> handleNomePastaJaCadastradoException(NomeJaExisteException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ObjectOptimisticLockingFailureException.class})
    public ModelAndView handleConflict(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("status", 409);
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
