package br.com.forum.config.swagger;

import br.com.forum.modelo.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket forumAPi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.forum"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Usuario.class);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API Lelton")
                .description("API forum")
                .contact(new Contact("Lelton", null, "lelton@lelton.com"))
                .license("Lelton use")
                .version("1.0").build();
    }
}
