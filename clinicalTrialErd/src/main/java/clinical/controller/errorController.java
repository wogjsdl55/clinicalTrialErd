package clinical.controller;

import clinical.service.bioInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class errorController implements ErrorController {

	private final String ERROR_PATH = "/error";

	@GetMapping(ERROR_PATH)
	public String redirectRoot(){
		return "index.html";
	}

	public String getErrorPath(){
		return null;
	}
}
