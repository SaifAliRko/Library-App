package com.libraryapp.spring_boot_library.config;

import com.libraryapp.spring_boot_library.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class myDataRestConfig implements RepositoryRestConfigurer {
  private String AllowedOrigins = "http://localhost:3000";

  @Override
  public void configureRepositoryRestConfiguration(
      org.springframework.data.rest.core.config.RepositoryRestConfiguration config,
      CorsRegistry corsRegistry) {

    HttpMethod[] unsupportedActions = {
      HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH
    };

    config.exposeIdsFor(Book.class);

    disableHttpMethods(Book.class, unsupportedActions, config);

    corsRegistry.addMapping(config.getBasePath() + "/**").allowedOrigins(AllowedOrigins);
  }

  private void disableHttpMethods(
      Class<?> theClass,
      HttpMethod[] unsupportedActions,
      org.springframework.data.rest.core.config.RepositoryRestConfiguration config) {

    config
        .getExposureConfiguration()
        .forDomainType(theClass)
        .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
        .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
  }
}
