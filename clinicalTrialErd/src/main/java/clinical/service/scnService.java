package clinical.service;

import clinical.common.common;
import clinical.data.memberEntity;
import clinical.data.scnVisitDto;
import clinical.data.scnVisitEntity;
import clinical.repository.memberRepository;
import clinical.repository.scnVisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class scnService {

	@Autowired scnVisitRepository ScnVisitRepository;
	@Autowired common Common;

	/*
	 * 방문일 등록 API
	 */
	@Transactional
	public Map<String, String> selectScnVisit(scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			scnVisitEntity visitInfo = ScnVisitRepository.findById(visit.getUser_email()).get();
			if(visitInfo != null &&!"".equals(visitInfo)){
				//entitiy -> dto
				visit = visit.scnVisitToDto(visitInfo);

				data.put("visit_seq", visit.getVisit_seq().toString());
				data.put("user_email", visit.getUser_email().toString());;
				data.put("visit_date", visit.getVisit_date().toString());

				result.put("data", mapper.writeValueAsString(data));
				result.put("resultCode",  "1");
				result.put("resultMsg", "저장되었습니다.");

			}
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}

		return result;
	}


	/*
	 * 방문일 등록 API
	 */
	@Transactional
	public Map<String, String> insScnVisit(scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			scnVisitEntity visitE = visit.toEntity();
	        //데이터 insert
			ScnVisitRepository.save(visitE);

			result.put("resultCode",  "1");
			result.put("resultMsg", "저장되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}

		return result; 
	
	}
	
}