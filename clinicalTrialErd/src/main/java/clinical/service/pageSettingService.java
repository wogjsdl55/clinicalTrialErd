package clinical.service;

import java.util.*;

import clinical.data.pageSettingDetailDto;
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
		List<String> dataEtc = new ArrayList<>();

		//데이터를 제대로 전달 받았는지 확인 string 형태로 파싱해줌
		ObjectMapper mapper = new ObjectMapper();

        try {
        	//페이지명으로 db에 select
			pageSettingEntity pageInfo = PageSettingRepository.findById(pageSetting.getGroup_name()).get();

			//detail
			if (pageInfo != null && !"".equals(pageInfo)) {

				//entitiy -> dto
				pageSetting = pageSetting.pageSettingToDto(pageInfo);
				List<pageSettingDetailEntity> detail = pageSetting.getPageSettingDetailEntity();
				for (pageSettingDetailEntity pageDetail : detail) {

					dataGroup.put("group_type", pageDetail.getGroup_type() );
					dataGroup.put("group_etc1", pageDetail.getPageSettingValueEntity().getEtc1());
					dataGroup.put("group_data", "");

					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc2());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc3());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc4());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc5());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc6());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc7());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc8());
					dataEtc.add(pageDetail.getPageSettingValueEntity().getEtc9());

					//null값 제거
					while (dataEtc.remove(null));
					dataGroup.put("group_etc", mapper.writeValueAsString(dataEtc));
					dataGroup.put("group_type", pageDetail.getGroup_type() );

					dataList.add(mapper.writeValueAsString(dataGroup));
					dataEtc.clear();
				}
				data.put("dataList", dataList.toString());
				data.put("seq", pageSetting.getSeq().toString());
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