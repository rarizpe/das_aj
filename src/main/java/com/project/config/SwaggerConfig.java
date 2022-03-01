package com.project.config;

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
@EnableSwagger2
//@ConfigurationProperties(prefix = "app.swagger")
public class SwaggerConfig {

    @Bean
    public Docket catalogo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Catálogo")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.catalogo"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(metadata());
    }

    @Bean
    public Docket seguridad() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Seguridad")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.seguridad"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .pathMapping("/")
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("DAS AJ")
                .description("Sistema de Administración de Negocios")
                .version("1.0") //TODO tomar los valores de application.properties
                .contact(new Contact(
                        "Elí Giacomelli",
                        "https://mail.google.com/mail/?view=cm&fs=1&to=rarizpee@gmail.com&su=Contacto%20DAS%20aj",
                        "rarizpee@gmail.com"
                ))
                .build();
    }
}