package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by anand on 20/4/17.
 */
@Configuration
@PropertySource("classpath:common-config.properties")
@ComponentScan("com.example")
public class CommonConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommonConfiguration.class);

    @Autowired
    Environment environment ;

    @Autowired
    private ApplicationContext applicationContext ;

    static{
        LOGGER.info("loading all the beans");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver ;
    }

    @Bean
    public String[] displayAllBeans(){
        for(String beanName : applicationContext.getBeanDefinitionNames()){
            LOGGER.info("bean : " + beanName.toString());
            System.out.println("bean : " + beanName.toString());
        }
        return applicationContext.getBeanDefinitionNames();
    }

    /*
    @Bean
    public RequestMappingHandlerMapping handlerMapping(){
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        return handlerMapping ;
    }
    */

}
