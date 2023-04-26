package clinical.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import clinical.common.common;
import clinical.data.bioInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class bioInfoService {

	//@Autowired memberRepository MemberRepository;
	@Autowired common Common;
	
	/*
	 * 의약품 임상시험 정보 조회 API
	 */
	@Transactional
	public Map<String, String> getBioInfo(String pageNo) {
		Map<String, String> result = new HashMap<>();
		Map<String, Object> bodyData = new HashMap<>();
		
        String jsonPrintString = null;
        try {
        	String url = "http://apis.data.go.kr/1471000/MdcinClincTestInfoService01/getMdcinClincTestInfoList01?";
        	String serviceKey = "rqlGv54I/NdoOyDHmtZlkuRcaIaFgVs9Y57aOqSoe3uoYiSyogVGIn6JyGJVELIyc89cY8zkfnKXVgg9IUGrmQ==";
        	String numOfRows = "1";
        	String type = "json";
        	
        	url = url + "serviceKey="+ serviceKey +"&numOfRows=" + numOfRows +"&pageNo="+ pageNo + "&type=" + type;  
        	
        	//RestTemplate Spring 3.0부터 지원하는 라이브러리로 간편하게 Rest 방식 API를 호출할 수 있습니다.
        	RestTemplate restTemplate = new RestTemplate();
        	
        	//header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
            HttpHeaders header = new HttpHeaders();

            HttpEntity<?> entity = new HttpEntity<>(header);
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);
            bodyData = (Map<String, Object>) resultMap.getBody();
            
            result.put("statusCode", Integer.toString(resultMap.getStatusCodeValue())); //http status code를 확인
            //result.put("header", resultMap.getHeaders().toString()); //헤더 정보 확인
            result.put("body", bodyData.get("body").toString()); //실제 데이터 정보 확인
            result.put("resultCode", "1"); //실제 데이터 정보 확인
            
            System.out.println(bodyData.get("body"));
        } catch (Exception e) {
        	result.put("resultCode", "99"); //실제 데이터 정보 확인
        	result.put("resultMsg", e.toString()); //실제 데이터 정보 확인
            e.printStackTrace();
        }

        return result;
   

	}
	
}