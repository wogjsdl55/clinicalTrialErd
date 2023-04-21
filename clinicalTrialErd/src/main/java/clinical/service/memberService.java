package clinical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinical.data.memberDto;
import clinical.data.memberEntity;
import clinical.repository.memberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class memberService {

	private final memberRepository MemberRepository = null;

	public String insMember(memberDto member) {
		String resultCode = "";
		//MemberRepository.save(member);

		return resultCode; 
	
	}
}