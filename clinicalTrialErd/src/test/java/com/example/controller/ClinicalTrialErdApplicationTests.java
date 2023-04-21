package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import clinical.data.memberEntity;
import clinical.repository.memberRepository;

@SpringBootTest
class ClinicalTrialErdApplicationTests {
	
	@Autowired
	private memberRepository MemberRepository;
    @Test
    public void testInsert(){
    // getClass() 메소드는 MemoRepository 인터페이스 타입의 실제 객체가 어떤것인지 확인한다
    	String user_email = "test@naver.com";
    	String user_name = "test";
    	String user_pwd = "testpwd";
    	//memberEntity member = memberEntity
    	
    	//MemberRepository.save(null)
    }   
}	
