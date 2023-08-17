package com.shah.multipledb.service;

import com.shah.multipledb.entity.oracle.Member;

import java.util.List;

public interface MemberService {

    List<Member> findMemberByNameUsingJpa(String name);

    List<Member> getAllMembersUsingJpa();
}

