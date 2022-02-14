package br.com.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile({"prod", "test"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    private AutenticacaoService autenticacaoService;
    private TokenService tokenService;

    @Autowired
    public SecurityConfigurations(AutenticacaoService autenticacaoService, TokenService tokenService) {
        this.autenticacaoService = autenticacaoService;
        this.tokenService = tokenService;
    }

    // Configurações de recursos estaticos (js, css, images, etc...)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**",
                        "/h2-console/**");
    }

    // Configurações de autenticações
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // Configurações de autorizações
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos", "/actuator/**").permitAll()
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("MODERADOR")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, autenticacaoService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
