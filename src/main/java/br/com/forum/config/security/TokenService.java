package br.com.forum.config.security;

import br.com.forum.modelo.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private Long expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {
        Usuario usuario = (Usuario) authenticate.getPrincipal();
        return Jwts.builder()
                .setIssuer("API do Forum")
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            getClaims(token);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    public String getEmailUsuario(String token){
        return getClaims(token).getSubject();
    }
}
