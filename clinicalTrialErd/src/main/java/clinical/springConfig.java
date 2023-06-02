package clinical;

import io.jsonwebtoken.io.IOException;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

//@Configuration
public class springConfig implements WebMvcConfigurer {
	//@Override
//	public void addCorsMappings(CorsRegistry registry) {
		//vue와 spring cros 이슈 해결
//		registry.addMapping("/**")
//		.allowedMethods("GET", "POST")
//	    .allowedOrigins("http://localhost:8080", "http://localhost:8081");
//    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**/*")
//                .addResourceLocations("classpath:/static/")
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//            @Override
//            protected Resource getResource(String resourcePath, Resource location) throw IOException {
//                Resource requestedResource = location.create
//            }
//
//        });
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }

}
