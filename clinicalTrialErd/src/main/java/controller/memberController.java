package controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repository.memberRepository;

@RestController
@RequestMapping("/member")
public class memberController {
	
	@Autowired(required=true)
	memberRepository MemberRepository;
	
	@PostMapping("getInfoList")
    public List getMemberInfoList() {
        return MemberRepository.findAll();
    }
	
//	@PostMapping("insInfo")
//	public String insMemberInfo() {
//		String resultCode = memberRepository.save();
//        return resultCode;
//    }

}

