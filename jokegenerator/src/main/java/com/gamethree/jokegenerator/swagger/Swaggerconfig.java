package com.gamethree.jokegenerator.swagger;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swaggerconfig {
	    @Bean
	    public OpenAPI jokeApi() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("🎭 Joke Generator API")
	                        .description("Random Joke Generator with MySQL persistence")
	                        .version("1.0.0"));
	    }
	
}
