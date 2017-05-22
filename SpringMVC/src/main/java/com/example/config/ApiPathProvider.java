package com.example.config;

/**
 * Created by anandkumar on 22/5/17.
 */
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;

/**
 * Created on 22/5/17.
 */
public class ApiPathProvider extends SwaggerPathProvider{
    @Autowired
    private ServletContext servletContext;

    private String docsLocation ;

    public ApiPathProvider(String docsLocation) {
        this.docsLocation = docsLocation ;
    }

    protected String applicationPath() {
        return this.getAppRoot().build().toString();
    }

    protected String getDocumentationPath() {
        return this.getAppRoot().path("/api-docs").build().toString();
    }

    private UriComponentsBuilder getAppRoot() {
        return UriComponentsBuilder.fromHttpUrl(docsLocation).path(this.servletContext.getContextPath());
    }
}
