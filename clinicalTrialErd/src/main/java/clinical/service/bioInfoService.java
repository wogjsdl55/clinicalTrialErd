package clinical.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import clinical.common.common;
import jakarta.transaction.Transactional;

@Service
public class bioInfoService {

	@Autowired common Common;
	
	/*
	 * 의약품 임상시험 정보 조회 API
	 */
	@Transactional
	public Map<String, String> getBioInfo(String pageNo) {
		Map<String, String> result = new HashMap<>();
		Map<String, Object> bodyData = new HashMap<>();
        String jsonPrintString = "";
        try {
        	String url = "http://apis.data.go.kr/1471000/MdcinClincTestInfoService01/getMdcinClincTestInfoList01?";
        	String serviceKey = "rqlGv54I/NdoOyDHmtZlkuRcaIaFgVs9Y57aOqSoe3uoYiSyogVGIn6JyGJVELIyc89cY8zkfnKXVgg9IUGrmQ==";
        	String numOfRows = "100";
        	String type = "json";
        	
        	url = url + "serviceKey="+ serviceKey +"&numOfRows=" + numOfRows +"&pageNo="+ pageNo + "&type=" + type;  
        	
        	//RestTemplate Spring 3.0부터 지원하는 라이브러리로 간편하게 Rest 방식 API를 호출할 수 있습니다.
        	RestTemplate restTemplate = new RestTemplate();
        	
        	//header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<?> entity = new HttpEntity<>(header);
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);
            bodyData =(Map<String, Object>) ((Map<String, Object>) resultMap.getBody()).get("body");
            
            result.put("statusCode", Integer.toString(resultMap.getStatusCodeValue())); //http status code를 확인
          //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
            ObjectMapper mapper = new ObjectMapper();
            
            result.put("totalCount", bodyData.get("totalCount").toString()); //실제 데이터 정보 확인
            result.put("items", mapper.writeValueAsString(bodyData.get("items")));
            result.put("resultCode", "1"); //실제 데이터 정보 확인
        } catch (Exception e) {
        	result.put("resultCode", "99"); //실제 데이터 정보 확인
        	result.put("resultMsg", e.getMessage()); //실제 데이터 정보 확인
        }

        return result;
   	}
	
}