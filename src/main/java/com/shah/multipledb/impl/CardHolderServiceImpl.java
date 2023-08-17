package com.shah.multipledb.impl;

import com.shah.multipledb.entity.mysql.CardHolder;
import com.shah.multipledb.repository.mysql.CardHolderRepository;
import com.shah.multipledb.service.CardHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardHolderServiceImpl implements CardHolderService {

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Override
    public List<CardHolder> findByMemberId(String memberId) {
        return cardHolderRepository.findByMemberName(memberId);
    }

    @Override
    public List<CardHolder> getAllCardholdersUsingJpa() {
        return cardHolderRepository.findAll();
    }

}
