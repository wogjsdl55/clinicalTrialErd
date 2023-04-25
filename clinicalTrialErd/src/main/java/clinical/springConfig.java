package clinical;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class springConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//vue와 spring cros 이슈 해결
		registry.addMapping("/**")
		.allowedMethods("GET", "POST")
	    .allowedOrigins("http://localhost:8080", "http://localhost:8081");
	}
	 
}
