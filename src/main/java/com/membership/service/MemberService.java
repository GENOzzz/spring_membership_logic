package com.membership.service;

import com.membership.domain.Member;
import com.membership.dto.MemberDTO;
import com.membership.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member save(MemberDTO memberdto){
        return memberRepository.save(memberdto.toEntity());
    }

    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName=String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    public Boolean checkId(String id){
        log.info(id);
        Member oldMember=memberRepository.getById(id);
        if(oldMember!=null){
            return false;
        }
        return true;
    }
}
