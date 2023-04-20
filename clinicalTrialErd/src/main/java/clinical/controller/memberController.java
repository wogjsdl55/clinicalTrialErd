package clinical.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinical.data.memberDto;
import clinical.data.memberEntity;
import clinical.service.memberService;

@RestController
@RequestMapping("/member")
public class memberController {
	
	@Autowired
	memberService MemberService;
	
	@PostMapping("insInfo")
    public String insMemberInfo(@RequestBody memberDto member) {
		String reulstCode = "";
		System.out.println("test="+ member);
		reulstCode = MemberService.insMember();
		
        return reulstCode;
    }
	
	@PostMapping("getTest")
    public String getMemberInfoTest() {
        return "1";
    }
	
//	@PostMapping("insInfo")
//	public String insMemberInfo() {
//		String resultCode = memberRepository.save();
//        return resultCode;
//    }

}

