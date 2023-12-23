package ru.yourbunny.yourbunny.configs;

import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
    // Шапка Swagger
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your Application API")
                .description("API documentation for Your Application")
                .version("1.0")
                .build();
    } // паттерн Строитель
}