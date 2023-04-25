package clinical.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinical.data.memberDto;
import clinical.service.memberService;

@RestController
@RequestMapping("/member")
public class memberController {
	
	@Autowired
	memberService MemberService;
	
	//회원가입 API
	@PostMapping("insMemberInfo")
    public String insMemberInfo(@RequestBody memberDto member) {
		String reulst = "";

		reulst = MemberService.insMember(member);
        return reulst;
    }
	
	//로그인 API
	@PostMapping("getMemberInfo")
    public String getMemberInfo(@RequestBody memberDto member) {
		String reulst = "";

		reulst = MemberService.getMember(member);
        return reulst;
    }
	 


}

