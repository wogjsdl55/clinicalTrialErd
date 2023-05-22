package clinical.controller;

import clinical.data.scnAdministrationDto;
import clinical.data.scnInvestigationDto;
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

	//방문일 삭제 API
	@PostMapping("visit/delete")
	public Map<String, String> deleteVisit(@RequestBody scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.delScnVisit(visit);
		return result;
	}


	//인구학적 조사 조회 API
	@PostMapping("investigation/select")
	public Map<String, String> selectInvestigation(@RequestBody scnInvestigationDto investigation) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.selectInvestigation(investigation);
		return result;
	}

	//인구학적 조사 저장 API
	@PostMapping("investigation/insert")
	public Map<String, String> insertInvestigation(@RequestBody scnInvestigationDto investigation) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.insScnInvestigation(investigation);
		return result;
	}

	//본제 투여 현황 저장 API
	@PostMapping("administration/insert")
	public Map<String, String> insertAdministration(@RequestBody scnAdministrationDto administration) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.insScnAdministration(administration);
		return result;
	}

	//본제 투여 현황 조회 API
	@PostMapping("administration/select")
	public Map<String, String> selectAdministration(@RequestBody scnAdministrationDto administration) {
		Map<String, String> result = new HashMap<>();

		result = ScnService.getAdministration(administration);
		return result;
	}


}

