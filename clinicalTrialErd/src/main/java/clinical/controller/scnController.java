package clinical.controller;

import clinical.data.scnVisitDto;
import clinical.service.scnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scn")
public class scnController {
	
	@Autowired scnService ScnService;

	//방문일 조회 API
	@PostMapping("visit/select")
	public Map<String, String> selectVisit(@RequestBody scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();
		result = ScnService.selectScnVisit(visit);
		return result;
	}

	//방문일 저장 API
	@PostMapping("visit/insert")
    public Map<String, String> insertVisit(@RequestBody scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.insScnVisit(visit);
        return result;
    }



}

