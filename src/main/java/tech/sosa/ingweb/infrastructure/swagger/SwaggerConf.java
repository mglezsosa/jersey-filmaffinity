package tech.sosa.ingweb.infrastructure.swagger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConf extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init();
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/jersey-filmaffinity/api");
        beanConfig.setResourcePackage("tech.sosa.ingweb.infrastructure.controller");
        beanConfig.setScan(true);
        
        beanConfig.setTitle("storr API");
        beanConfig.setDescription("This REST API exposes the basic functions of storr. " +
                "storr is a NoSQL store intended to provide easy storage of arbitrary tuples");
	}
}
