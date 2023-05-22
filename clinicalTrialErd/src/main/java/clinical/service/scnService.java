package clinical.service;

import clinical.common.common;
import clinical.data.*;
import clinical.repository.memberRepository;
import clinical.repository.scnAdministrationRepository;
import clinical.repository.scnInvestigationRepository;
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
	@Autowired scnInvestigationRepository ScnInvestigationRepository;
	@Autowired scnAdministrationRepository ScnAdministrationRepository;
	@Autowired common Common;

	/*
	 * 방문일 조회 API
	 */
	@Transactional
	public Map<String, String> selectScnVisit(scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Dto -> Entity로 변환
			scnVisitEntity visitE = visit.toEntity();

			scnVisitEntity visitInfo = ScnVisitRepository.findById(visitE.getUser_email()).get();
			if(visitInfo != null &&!"".equals(visitInfo)){
				//entitiy -> dto
				visit = visit.scnVisitToDto(visitInfo);

				data.put("user_email", visit.getUser_email().toString());;
				data.put("visit_date", visit.getVisit_date().toString());

				result.put("resultCode",  "1");
				result.put("data", mapper.writeValueAsString(data));
			}else {
				result.put("resultCode",  "0");
				result.put("data", "");
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


	/*
	 * 방문일 삭제 API
	 */
	@Transactional
	public Map<String, String> delScnVisit(scnVisitDto visit) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			scnVisitEntity visitE = visit.toEntity();
			//데이터 delete
			ScnVisitRepository.deleteById(visitE.getUser_email());

			result.put("resultCode",  "1");
			result.put("resultMsg", "삭제되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}

		return result;

	}

	/*
	 * 인구학적 조사 등록 API
	 */
	@Transactional
	public Map<String, String> insScnInvestigation(scnInvestigationDto investigation) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			scnInvestigationEntity investigationE = investigation.toEntity();
			//데이터 insert
			ScnInvestigationRepository.save(investigationE);

			result.put("resultCode",  "1");
			result.put("resultMsg", "저장되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}



	/*
	 * 인구학적 조사 조회 API
	 */
	@Transactional
	public Map<String, String> selectInvestigation(scnInvestigationDto investigation) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Dto -> Entity로 변환
			scnInvestigationEntity investigationE = investigation.toEntity();

			investigationE = ScnInvestigationRepository.findById(investigation.getUser_email()).get();
			if(investigationE != null &&!"".equals(investigationE)){
				//entitiy -> dto
				investigation = investigation.scnInvestigationToDto(investigationE);

				data.put("user_email", investigation.getUser_email().toString());;
				data.put("age", investigation.getAge().toString());
				data.put("birthday", investigation.getBirthday().toString());
				data.put("ethnic", investigation.getEthnic().toString());
				data.put("gender", investigation.getGender().toString());
				data.put("races", investigation.getRaces().toString());
				data.put("time_birth", investigation.getTime_birth().toString());
				data.put("regdate", investigation.getRegdate().toString());

				result.put("resultCode",  "1");
				result.put("data", mapper.writeValueAsString(data));
			}else {
				result.put("resultCode",  "0");
				result.put("data", "");
			}
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}


	/*
	 * 본제 투여 현황 저장 API
	 */
	@Transactional
	public Map<String, String> insScnAdministration(scnAdministrationDto administration) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			scnAdministrationEntity administrationE = administration.toEntity();
			//데이터 insert
			ScnAdministrationRepository.save(administrationE);

			result.put("resultCode",  "1");
			result.put("resultMsg", "저장되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}



	/*
	 * 본제 투여 현황 조회 API
	 */
	@Transactional
	public Map<String, String> getAdministration(scnAdministrationDto administration) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Dto -> Entity로 변환
			scnAdministrationEntity administrationE = administration.toEntity();

			scnAdministrationEntity administrationInfo = ScnAdministrationRepository.findById(administrationE.getUser_email()).get();
			if(administrationInfo != null &&!"".equals(administrationInfo)){
				//entitiy -> dto
				administration = administration.scnAdministrationToDto(administrationInfo);

				data.put("user_email", administration.getUser_email().toString());
				data.put("injection_yn", administration.getInjection_yn().toString());
				data.put("injection_start_date", administration.getInjection_start_date().toString());
				data.put("injection_start_time", administration.getInjection_start_time().toString());
				data.put("injection_end_date", administration.getInjection_end_date().toString());
				data.put("injection_end_time", administration.getInjection_end_time().toString());
				data.put("injection_date", administration.getInjection_date().toString());
				data.put("unit", administration.getUnit().toString());
				data.put("injection_cnt", administration.getInjection_cnt().toString());
				data.put("route", administration.getRoute().toString());
				data.put("rate_change", administration.getRate_change().toString());
				data.put("rate_reason", administration.getRate_reason().toString());
				data.put("regdate", administration.getRegdate().toString());

				result.put("resultCode",  "1");
				result.put("data", mapper.writeValueAsString(data));
			}else {
				result.put("resultCode",  "0");
				result.put("data", "");
			}
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}
}