package clinical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.data.memberDto;
import clinical.data.memberEntity;
import clinical.repository.memberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class memberService {

	@Autowired memberRepository MemberRepository;
	
	/*
	 * 회원가입 API
	 */
	@Transactional
	public String insMember(memberDto member) {
		String result = "";
		try {
			//Dto -> Entity로 변환
			memberEntity memberE = member.toEntity();
			
			//이메일 중복체크
	        if (!MemberRepository.findById(member.getUser_email()).isEmpty()) {
	            return result ="이미 존재하는 이메일입니다.";
	        }
			
	        //데이터 insert
			MemberRepository.save(memberE);
			result ="회원가입이 완료되었습니다.";
		} catch (Exception e) {
			result = "0";
		}

		return result; 
	
	}
	
	
	/*
	 * 로그인 API
	 */
	@Transactional
	public String getMember(memberDto member) {
		String result = "";
		try {
			//Dto -> Entity로 변환
			memberEntity memberE = member.toEntity();
			
			//입력한 메일로 db에 select
			Optional<memberEntity> userInfo = MemberRepository.findById(member.getUser_email());
			if (userInfo != null && !userInfo.isEmpty()) {				
				String userPwdInfo = userInfo.get().getUser_pwd().toString();

				//비밀번호 체크
				if (!userPwdInfo.equals(member.getUser_pwd())) {
					return result ="입력하신 비밀번호가 일치하지 않습니다.";
				}
							
				 
				result = "1";
	        }else {
	        	//이메일 체크
	        	result ="로그인 정보가 존재하지 않습니다."; 
	        }
			

		} catch (Exception e) {
			result = "0";
		}

		return result; 
	
	}
}