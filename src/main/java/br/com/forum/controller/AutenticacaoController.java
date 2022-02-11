package br.com.forum.controller;

import br.com.forum.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity<Void> autenticar(@RequestBody @Valid LoginForm loginForm){
        System.out.println(loginForm.getEmail());
        System.out.println(loginForm.getSenha());

        return ResponseEntity.notFound().build();
    }
}
