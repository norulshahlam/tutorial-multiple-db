package com.shah.multipledb.impl;

import com.shah.multipledb.entity.oracle.Member;
import com.shah.multipledb.repository.oracle.MemberRepository;
import com.shah.multipledb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> findMemberByNameUsingJpa(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public List<Member> getAllMembersUsingJpa() {
        return memberRepository.findAll();
    }

}
