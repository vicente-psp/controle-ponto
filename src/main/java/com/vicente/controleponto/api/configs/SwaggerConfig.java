package com.vicente.controleponto.api.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.vicente.controleponto.api"))
	        .paths(PathSelectors.any())
	        .build()
	        .useDefaultResponseMessages(false)
//	        .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
	        .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
//	        .securityContexts(Arrays.asList(securityContext()))
	        .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Meu Controle")
	            .description("Este documento descreve a API REST da aplicação Meu Controle")
	            .version("1.0.0")
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
	            .contact(new Contact("Vicente P S Penha", "https://www.linkedin.com/in/vicente-psp/", "vicente.pspenha@gmail.com"))
            .build();
	}
	
}
