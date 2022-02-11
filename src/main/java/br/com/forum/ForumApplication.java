package br.com.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

// Anotação para registrar automaticamente os seguintes beans para uso com Spring MVC
// DomainClassConverter, PageableHandlerMethodArgumentResolver, outros.
@EnableSpringDataWebSupport
@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}
