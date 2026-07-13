package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosLoginUsuario;
import com.minigoodreads.api.DTO.response.LoginToken;
import com.minigoodreads.api.infra.security.TokenService;
import com.minigoodreads.api.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DadosLoginUsuario dados) {
        var loginSenha = new UsernamePasswordAuthenticationToken(dados.nick(), dados.senha());
        var autenticacao = authenticationManager.authenticate(loginSenha);

        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new LoginToken(token));
    }
}
