package clinical.service;

import clinical.common.common;
import clinical.common.systemQuery;
import clinical.data.*;
import clinical.repository.scnAdministrationRepository;
import clinical.repository.scnInvestigationRepository;
import clinical.repository.scnVisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class scnService {

	@Autowired scnVisitRepository ScnVisitRepository;
	@Autowired scnInvestigationRepository ScnInvestigationRepository;
	@Autowired scnAdministrationRepository ScnAdministrationRepository;
	@Autowired common Common;
	@Autowired systemQuery SystemQuery;

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

			scnVisitEntity visitInfo = ScnVisitRepository.findById(visitE.getUser_email()).orElse(new scnVisitEntity());

			if(visitInfo.getUser_email() != null &&!"".equals(visitInfo.getUser_email())){
				//entitiy -> dto
				visit = visit.scnVisitToDto(visitInfo);

				data.put("user_email", visit.getUser_email().toString());
				data.put("visit_date", visit.getVisit_date().toString());

				result.put("resultCode",  "1");
			}else { result.put("resultCode",  "0");	}

			result.put("data", mapper.writeValueAsString(data));
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
		scnVisitDto visit = new scnVisitDto();
		LocalDate visitDate;
		try {
			//Dto -> Entity로 변환
			scnInvestigationEntity investigationE = investigation.toEntity();
			scnVisitEntity visitE = ScnVisitRepository.findById(investigation.getUser_email()).orElse(new scnVisitEntity());

			if(visitE.getUser_email() != null && !"".equals(visitE.getUser_email())){

				if (!SystemQuery.dateCheck(visitE.getVisit_date(), investigationE.getBirthday())) {

					result.put("resultCode",  "0");
					result.put("resultMsg",  "방문일 데이터가 생년월일 이전입니다.");
					return result;
				};

				//데이터 insert
				ScnInvestigationRepository.save(investigationE);
				result.put("resultCode",  "1");
				result.put("resultMsg", "저장되었습니다.");
			}else{
				result.put("resultCode",  "0");
				result.put("resultMsg",  "방문일 데이터가 없습니다.");
			}
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
			investigationE = ScnInvestigationRepository.findById(investigation.getUser_email()).orElse(new scnInvestigationEntity());

			if(investigationE.getUser_email() != null && !"".equals(investigationE.getUser_email())){
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
			}else { result.put("resultCode",  "0"); }

			result.put("data", mapper.writeValueAsString(data));
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
		scnVisitDto visit = new scnVisitDto();
		String visitDate = "";
		try {
			scnInvestigationEntity investigationE = ScnInvestigationRepository.findById(administration.getUserEmail()).orElse(new scnInvestigationEntity());
			//Dto -> Entity로 변환
			scnAdministrationEntity administrationE = administration.toEntity();

			//investigationCheck (체크변수, 체크값,체크변수, 체크값)
			if (SystemQuery.investigationCheck(investigationE.getGender() ,"남성", administrationE.getInjection_yn(),"예")) {

				result.put("resultCode",  "0");
				result.put("resultMsg",  "인구학적 조사에 성별이 남성이면 본제 투여를 하실 수 없습니다.");
				return result;
			};

			//investigationCheck (체크변수, 체크값,체크변수, 체크값)
			if (SystemQuery.dateCheck(administrationE.getInjection_start_date(), administrationE.getInjection_end_date())) {

				result.put("resultCode",  "0");
				result.put("resultMsg",  "투여 시작일보다 종료일이 빠를순 없습니다.");
				return result;
			};
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
		List<String> dataList = new ArrayList<>();
		try {
			//Dto -> Entity로 변환
			scnAdministrationEntity administrationE = administration.toEntity();
			List<scnAdministrationEntity> administrationInfo = ScnAdministrationRepository.findByUserEmailOrderBySeqAsc(administration.getUserEmail());

			if(administrationInfo != null &&!"".equals(administrationInfo)){

				for (scnAdministrationEntity info : administrationInfo) {

					data.put("user_email", info.getUserEmail());
					data.put("seq", info.getSeq().toString());
					data.put("injection_yn", info.getInjection_yn().toString());
					data.put("injection_start_date", info.getInjection_start_date().toString());
					data.put("injection_start_time", info.getInjection_start_time().toString());
					data.put("injection_end_date", info.getInjection_end_date().toString());
					data.put("injection_end_time", info.getInjection_end_time().toString());
					data.put("injection_date", info.getInjection_date().toString());
					data.put("unit", info.getUnit().toString());
					data.put("injection_cnt", info.getInjection_cnt().toString());
					data.put("route", info.getRoute().toString());
					data.put("rate_change", info.getRate_change().toString());
					data.put("rate_reason", info.getRate_reason().toString());
					data.put("regdate", info.getRegdate().toString());

					dataList.add(mapper.writeValueAsString(data));
				}
				result.put("resultCode",  "1");
			}else { result.put("resultCode",  "0"); }

			result.put("data", mapper.writeValueAsString(dataList));
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}


	/*
	 * 본제 투여 현황 삭제 API
	 */
	@Transactional
	public Map<String, String> deleteAdministration(scnAdministrationDto administration) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			scnAdministrationEntity administrationE = administration.toEntity();
			//데이터 delete
			ScnAdministrationRepository.deleteById(administrationE.getSeq().toString());

			result.put("resultCode",  "1");
			result.put("resultMsg", "삭제되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}
		return result;
	}
}