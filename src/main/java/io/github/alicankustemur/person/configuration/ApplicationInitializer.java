package io.github.alicankustemur.person.configuration;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class ApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationConfiguration.class);

		ServletRegistration.Dynamic facesServlet = container.addServlet("Faces Servlet", new FacesServlet());
		facesServlet.setLoadOnStartup(1);
		facesServlet.addMapping("/faces/*");
		facesServlet.addMapping("*.xhtml");
		facesServlet.addMapping("*.jsf");

		container.addListener(new ContextLoaderListener(applicationContext));
		container.addListener(new EnvironmentLoaderListener());
		
		
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
			
		container.addFilter("ShiroFilter", new ShiroFilter()).addMappingForUrlPatterns(dispatcherTypes, true, "/*");;
		
		container.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
		container.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		container.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");

	}
	
	@Bean
	public SecurityManager shiroFactoryBean(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		return factory.getInstance();
	}

}
