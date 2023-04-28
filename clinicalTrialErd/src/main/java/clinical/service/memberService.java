package clinical.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.common.common;
import clinical.data.memberDto;
import clinical.data.memberEntity;
import clinical.repository.memberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class memberService {

	@Autowired memberRepository MemberRepository;
	@Autowired common Common;
	
	/*
	 * 회원가입 API
	 */
	@Transactional
	public Map<String, String> insMember(memberDto member) {
		Map<String, String> result = new HashMap<>();
		try {
			//Dto -> Entity로 변환
			memberEntity memberE = member.toEntity();
			
			//이메일 중복체크
	        if (!MemberRepository.findById(member.getUser_email()).isEmpty()) {
	        	result.put("resultCode",  "0");
				result.put("resultMsg", "이미 존재하는 이메일입니다.");
	            return result;
	        }

	        //비밀번호 암호화
	        memberE.setUser_pwd(Common.encryptor(member.getUser_pwd()));
	        
	        //데이터 insert
			MemberRepository.save(memberE);

			result.put("resultCode",  "1");
			result.put("resultMsg", "회원가입이 완료되었습니다.");
		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}

		return result; 
	
	}
	
	
	/*
	 * 로그인 API
	 */
	@Transactional
	public Map<String, String> getMember(memberDto member) {
		Map<String, String> result = new HashMap<>();
		String accessToken ="";
		try {
			//입력한 메일로 db에 select
			Optional<memberEntity> userInfo = MemberRepository.findById(member.getUser_email());
			if (userInfo != null && !userInfo.isEmpty()) {				
				String userPwdInfo = Common.decrypt(userInfo.get().getUser_pwd()).toString();

				//비밀번호 체크
				if (!userPwdInfo.equals(member.getUser_pwd())) {
					result.put("resultCode",  "0");
					result.put("resultMsg", "입력하신 비밀번호가 일치하지 않습니다.");
					return result;
				}
				
				//로그인시 토큰 값 생성 후 전달
				accessToken = Common.createToken(); 
				result.put("accessToken",  accessToken);
				result.put("resultCode",  "1");
	        }else {
	        	//이메일 체크
	        	result.put("resultCode",  "0");
	        	result.put("resultMsg",  "로그인 정보가 존재하지 않습니다"); 
	        }
			

		} catch (Exception e) {
			result.put("resultCode",  "99");
			result.put("resultMsg",  e.getMessage());
		}

		return result; 
	
	}
}