package com.membership.controller;


import com.membership.domain.Member;
import com.membership.dto.MemberDTO;
import com.membership.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(){
        log.info("일단 signupForm은 호출됨");
        return "signForm";
    }

    @GetMapping("/test")
    public String testForm(){
        return "test1";
    }

    @PostMapping("/signup")
    public String saveMember(MemberDTO memberDTO){
        log.info("post 로 호출됨");
        Member saveMember=memberService.save(memberDTO);
        log.info("Entity id={} name={}",saveMember.getId(),saveMember.getName());
        return "signUpSuccess";
    }

}
