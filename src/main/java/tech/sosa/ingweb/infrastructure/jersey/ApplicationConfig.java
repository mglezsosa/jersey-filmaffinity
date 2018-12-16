package tech.sosa.ingweb.infrastructure.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		packages(
				"tech.sosa.ingweb.infrastructure.controller", 
				"io.swagger.jaxrs.listing");
        
		register(new DependencyBinder());
	}
}
