package webservice.rest;

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/api")
public class apiConfig extends Application {

  public apiConfig(@Context ServletConfig servletConfig) {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion("1.0.0");
    beanConfig.setTitle("Vacunas UY API");
    beanConfig.setSchemes(new String[] { "http", "https" });
    //        beanConfig.setHost("localhost:8080");
    beanConfig.setHost("vacunasuyg16.web.elasticloud.uy");
    beanConfig.setBasePath("/VacunasUYG16-api/api");
    beanConfig.setResourcePackage("webservice.rest");
    beanConfig.setScan(true);
  }
}
