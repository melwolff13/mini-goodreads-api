package com.minigoodreads.api.infra.exception;

import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.exceptions.DadosErro;
import com.minigoodreads.api.exceptions.RegraDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratarErroGenerico(Exception e) {
        logger.error("Erro não tratado: {}", e.getMessage());
        var erro = new DadosErro("INTERNAL_ERROR", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> tratarIllegalArgument(IllegalArgumentException e) {
        var erro = new DadosErro("BAD_REQUEST", e.getMessage());
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> tratarIntegridade(DataIntegrityViolationException e) {
        var erro = new DadosErro("CONFLICT", "Não foi possível concluir a operação por violação da integridade de dados");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> tratarPermissao(AccessDeniedException e) {
        var erro = new DadosErro("UNAUTHORIZED", e.getMessage());
        return ResponseEntity.status((HttpStatus.FORBIDDEN)).body(erro);
    }

}
