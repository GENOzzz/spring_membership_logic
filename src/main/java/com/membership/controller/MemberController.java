package com.membership.controller;


import com.membership.domain.Member;
import com.membership.dto.MemberDTO;
import com.membership.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(){
        return "signForm";
    }

    @GetMapping("/test")
    public String testForm(){
        return "test1";
    }

    @PostMapping("/signup")
    public String saveMember(@Valid MemberDTO memberDTO, Errors errors, Model model){
        if(errors.hasErrors()){
//            회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("memberDTO",memberDTO);
//            유효성 통과 못한 필드와 메세지를 핸들링
            Map<String,String> validatorResult = memberService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key,validatorResult.get(key));
            }
//            회원가입 페이지로 리턴
            return "signForm";
        }
        model.addAttribute("memberDTO",memberDTO);
        Member saveMember=memberService.save(memberDTO);
        log.info("Entity id={} name={}",saveMember.getId(),saveMember.getName());
        return "signUpSuccess";
    }

    @PostMapping("/signup/checkID")
    @ResponseBody
    public int checkId(String id){
        if(id.length()==0){
            return 3;
        }
        if(id.length()<2) {
            return 2;
        }
        if(memberService.checkId(id)) {
            return 1;
        }else{
            return 0;
        }

    }

}
