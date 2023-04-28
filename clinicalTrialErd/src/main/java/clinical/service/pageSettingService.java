package clinical.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.common.common;
import clinical.data.memberEntity;
import clinical.data.pageSettingDto;
import clinical.data.pageSettingEntity;
import clinical.repository.pageSettingRepository;
import jakarta.transaction.Transactional;

@Service
public class pageSettingService {

	@Autowired pageSettingRepository PageSettingRepository;
	@Autowired common Common;
	
	/*
	 * 의약품 임상시험 정보 조회 API
	 */
	@Transactional
	public Map<String, String> getPageSetting(Map<String, String> pageSetting) {
		Map<String, String> result = new HashMap<>();
		
        try { 
        	
        	//입력한 메일로 db에 select
			Optional<pageSettingEntity> pageInfo = PageSettingRepository.findById(pageSetting.get("page_name"));

			if (pageInfo != null && !pageInfo.isEmpty()) {
				System.out.println(pageInfo.get());
	
	        	result.put("resultData", pageInfo.get().toString());
	            result.put("resultCode", "1"); //실제 데이터 정보 확인
			}else {
				result.put("resultCode", "0"); //데이터 없음
			}
            
        } catch (Exception e) {
        	result.put("resultCode", "99"); //오류
        	result.put("resultMsg", e.getMessage());
        }

        return result;
	}
	
}