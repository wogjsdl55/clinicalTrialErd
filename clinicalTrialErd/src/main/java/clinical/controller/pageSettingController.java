package clinical.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinical.data.pageSettingDto;
import clinical.service.pageSettingService;

@RestController
@RequestMapping("/pageSetting")
public class pageSettingController {
	
	@Autowired pageSettingService PageSettingService;
	
	//페이지 구성 항목 조회
	@PostMapping("getInfo")
    public Map<String, String> pageSetting(@RequestBody pageSettingDto pageSetting) {
		Map<String, String> result = new HashMap<>();
		
		result = PageSettingService.getPageSetting(pageSetting);
        return result;
    }

}
