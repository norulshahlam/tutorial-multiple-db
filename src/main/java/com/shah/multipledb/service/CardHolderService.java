package com.shah.multipledb.service;

import com.shah.multipledb.entity.mysql.CardHolder;

import java.util.List;

public interface CardHolderService {

    List<CardHolder> findByMemberId(String memberId);

    List<CardHolder> getAllCardholdersUsingJpa();
}

