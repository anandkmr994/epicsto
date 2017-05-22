package com.example.config;


import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created on 18/5/17.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Value("${app.docs}")
    private String docsLocation ;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns("/.*")
                .pathProvider(apiPathProvider());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("user API",
                "API for user",
                "user API terms of service",
                "demo@paytm.com",
                "paytm",
                "");
        return apiInfo;
    }

    /**
     * Example of a custom path provider
     */
    @Bean
    public ApiPathProvider apiPathProvider() {
        ApiPathProvider apiPathProvider = new ApiPathProvider(docsLocation);
        return apiPathProvider;
    }
}
