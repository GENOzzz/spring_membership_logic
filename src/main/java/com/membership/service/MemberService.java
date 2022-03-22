package com.membership.service;

import com.membership.domain.Member;
import com.membership.dto.MemberDTO;
import com.membership.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

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
}
