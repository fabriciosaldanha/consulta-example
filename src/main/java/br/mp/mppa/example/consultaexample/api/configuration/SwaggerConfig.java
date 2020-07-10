package br.mp.mppa.example.consultaexample.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ConfigurationProperties(value = "swagger.ui")
public class SwaggerConfig {
    @Bean
    public Docket gesContratoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Exemplo de Consulta Parametrizada",
                "API com exemplos de construção de consultas parametrizadas com implementadas de formas diferentes.",
                "1.0.0",
                null,
                new Contact("DAP - Divisão de Análise e Programação", "", "dap@mppa.mp.br"),
                "",
                "",
                new ArrayList<>());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(false)
                .displayOperationId(false)
                .defaultModelsExpandDepth(-1)
                .defaultModelExpandDepth(-1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .filter(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}