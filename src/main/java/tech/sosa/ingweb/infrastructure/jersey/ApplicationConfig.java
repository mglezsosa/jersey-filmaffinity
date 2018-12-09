package tech.sosa.ingweb.infrastructure.jersey;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		packages(
				"tech.sosa.ingweb.infrastructure.controller", 
				"io.swagger.jaxrs.listing");
		
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/java-web-service/api");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);
        
		register(new DependencyBinder());
	}
}
