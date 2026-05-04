package br.eng.jonathan.ntoerp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DefaultLinkRelationProvider;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Geriluh API")
                        .version("1.0")
                        .description("Geriluh is a micro-ERP created for Lu Caseirinho. It is a complete and customizable business management system designed to meet the needs of small and medium-sized businesses. \n\n" + "O Geriluh é um micro-ERP criado para a Lu Caseirinho e é um sistema de gestão empresarial completo e personalizável, pensado para atender às necessidades de pequenas e médias empresas.")

                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public LinkRelationProvider linkRelationProvider() {
        return new DefaultLinkRelationProvider();
    }
}