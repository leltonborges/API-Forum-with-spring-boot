package br.com.forum.controller;

import br.com.forum.config.security.TokenService;
import br.com.forum.controller.form.LoginForm;
import br.com.forum.dto.token.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Profile({"prod", "test"})
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm loginForm){
        try {
        UsernamePasswordAuthenticationToken authenticationToken = loginForm.converter();
        Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);

        String token = tokenService.gerarToken(authenticate);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        }catch (AuthenticationException ex){
            return  ResponseEntity.badRequest().build();
        }
    }
}
