package net.app.goodreview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptioneHandler {
    //@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity autenticationExceptionHandle(AuthenticationException exception) {
        Map<Object, Object> response = new HashMap<>();

        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(response);
    }
}
