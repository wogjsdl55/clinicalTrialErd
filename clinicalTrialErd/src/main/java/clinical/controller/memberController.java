package clinical.controller;

import java.util.HashMap;
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
	
	@Autowired memberService MemberService;
	
	//회원가입 API
	@PostMapping("insMemberInfo")
    public Map<String, String> insMemberInfo(@RequestBody memberDto member) {
		Map<String, String> result = new HashMap<>();

		result = MemberService.insMember(member);
        return result;
    }
	
	//로그인 API
	@PostMapping("getMemberInfo")
    public Map<String, String> getMemberInfo(@RequestBody memberDto member) {
		Map<String, String> result = new HashMap<>();
		result = MemberService.getMember(member);
        return result;
    }
	 


}

