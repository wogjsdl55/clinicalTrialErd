package clinical.service;

import java.util.*;

import clinical.data.pageSettingDetailEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.common.common;
import clinical.data.pageSettingDto;
import clinical.data.pageSettingEntity;
import clinical.repository.pageSettingRepository;
import jakarta.transaction.Transactional;

@Service
public class pageSettingService {

	@Autowired pageSettingRepository PageSettingRepository;
	@Autowired common Common;
	
	/*
	 * 페이지 셋팅
	 */
	@Transactional
	public Map<String, String> getPageSetting(pageSettingDto pageSetting) {
		List<String> dataList = new ArrayList<>();
		Map<String, String> result = new HashMap<>();
		Map<String, String> data = new HashMap<>();
		Map<String, String> dataGroup = new HashMap<>();
		//데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
		ObjectMapper mapper = new ObjectMapper();

        try {
        	//입력한 메일로 db에 select
			pageSettingEntity pageInfo = PageSettingRepository.findById(pageSetting.getGroup_name()).get();

			//detail
			if (pageInfo != null && !"".equals(pageInfo)) {

				//entitiy -> dto
				pageSetting = pageSetting.pageSettingToDto(pageInfo);

				List<pageSettingDetailEntity> detail = pageSetting.getPageSettingDetailEntity();
				for (pageSettingDetailEntity PageSettingDetailEntity : detail) {

					System.out.println(PageSettingDetailEntity.getSeq());
					//dataGroup.put("group_type", pageSetting.getPageSettingDetailEntity(). );
//					dataGroup.put("group_etc1", pageSetting.getPageSettingDetailEntity().get(i).getEtc1());
//					dataGroup.put("group_etc2", pageSetting.getPageSettingDetailEntity().get(i).getEtc2());
//					dataGroup.put("group_etc3", pageSetting.getPageSettingDetailEntity().get(i).getEtc3());
//					dataGroup.put("group_etc4", pageSetting.getPageSettingDetailEntity().get(i).getEtc4());
//					dataGroup.put("group_etc5", pageSetting.getPageSettingDetailEntity().get(i).getEtc5());

					dataList.add(mapper.writeValueAsString(dataGroup));

				}
				data.put("dataList", dataList.toString());
				data.put("seq", pageSetting.getSeq());
				data.put("group_name", pageSetting.getGroup_name());

				result.put("data", mapper.writeValueAsString(data) ); //실제 데이터 정보 확인
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