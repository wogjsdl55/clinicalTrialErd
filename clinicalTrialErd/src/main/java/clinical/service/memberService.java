package clinical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.repository.memberRepository;

@Service
public class memberService {
	
	@Autowired
	memberRepository MemberRepository;
	
	public String insMember() {
		String test = "1";
		//MemberRepository.save(memberEntity)
		return test; 
	  }
}
