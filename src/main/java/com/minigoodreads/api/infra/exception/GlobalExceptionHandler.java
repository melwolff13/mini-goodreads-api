package com.minigoodreads.api.infra.exception;

import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.exceptions.DadosErro;
import com.minigoodreads.api.exceptions.RegraDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(EntityNotFoundException e) {
        var erro = new DadosErro("NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<?> tratarRegraDeNegocio(RegraDeNegocioException e) {
        var erro = new DadosErro("BAD_REQUEST", e.getErros());
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException e) {
        var mensagem = e.getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();
        var erro = new DadosErro("VALIDATION_ERROR", mensagem);
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<?> tratarConflito(ConflitoException e) {
        var erro = new DadosErro("CONFLICT", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
