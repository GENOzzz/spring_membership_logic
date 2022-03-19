package com.membership.service;

import com.membership.domain.Member;
import com.membership.dto.MemberDTO;
import com.membership.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member save(MemberDTO memberdto){
        return memberRepository.save(memberdto.toEntity());
    }

}
