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
	public Map<String, String> getPageSetting(pageSettingDto pageSetting) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		
        try { 
        	
        	//입력한 메일로 db에 select
        	System.out.println("test1="+ pageSetting.getPage_name());
			pageSettingEntity pageInfo = PageSettingRepository.findById(pageSetting.getPage_name()).get();
			System.out.println("test2="+ pageInfo);
			if (pageInfo != null && !"".equals(pageInfo)) {
				
				pageSetting = pageSetting.pageSettingToDto(pageInfo);
				
				data.put("page_name", pageSetting.getPage_name());
				data.put("page_type_01", pageSetting.getPage_type_01());
				data.put("page_type_02", pageSetting.getPage_type_02());
				data.put("page_type_03", pageSetting.getPage_type_03());
				data.put("page_type_04", pageSetting.getPage_type_04());
				data.put("page_type_05", pageSetting.getPage_type_05());
	        	
				result.put("data", data.toString()); //실제 데이터 정보 확인
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