package cl.ecomarket.monitoreo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("Ecomarket Monitoreo API")
                .version("1.0.0")
                .description("Documentación de la API de Monitoreo de Ecomarket"));
    }
}
