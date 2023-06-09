package clinical.controller;

import clinical.service.bioInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bioInfo")
public class bioInfoController {
	
	@Autowired bioInfoService BionBioInfoService;
	
	//의약품 임상시험 정보 조회 API
	@GetMapping("getInfo")
    public Map<String, String> getBioInfo(String pageNo) {
		Map<String, String> result = new HashMap<>();

		result = BionBioInfoService.getBioInfo(pageNo);
        return result;
    }
}
