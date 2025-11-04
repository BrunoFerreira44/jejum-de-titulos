package br.com.bruno.ultimos_campeoes.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${FRONTEND_URL:http://127.0.0.1:5500}") // fallback para ambiente local
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Todos os endpoints
                .allowedOrigins(frontendUrl) // Frontend permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowCredentials(true); // Permite cookies/sessões
    }
}
