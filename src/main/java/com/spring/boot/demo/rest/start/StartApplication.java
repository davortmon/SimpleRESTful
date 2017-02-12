package com.spring.boot.demo.rest.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.*;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.spring.boot.demo.rest.repository")
@ComponentScan({"com.spring.boot.demo.rest"})
@EntityScan("com.spring.boot.demo.rest.repository.model")
@EnableSwagger2
public class StartApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartApplication.class, args);
    }

    /**
     * Bean init Swagger.
     * @return Object init Swagger.
     */
    @Bean
    public Docket newsApi() {
        
    	return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("api-user")
	        .apiInfo(apiInfo())
	        .select()
	        .paths(regex("/api.*"))
	        .build();
	        
    }
    
    /**
     * Set info Swagger.
     * @return Info Swagger.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot RESTful Sevice with Swagger")
                .description("Spring Boot RESTful Sevice with Swagger")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("David Ortiz")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
    
    /**
     * Bean Configuration for disabled ValidationURL for Swagger.
     * @return
     */
    @Bean
    UiConfiguration uiConfig() {
    	return new UiConfiguration(null);
    }
}
