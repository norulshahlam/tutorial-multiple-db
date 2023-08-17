package com.shah.multipledb.impl;

import com.shah.multipledb.entity.postgres.Card;
import com.shah.multipledb.repository.postgres.CardRepository;
import com.shah.multipledb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NORUL
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> findByName(String name) {
        return cardRepository.findByName(name);
    }

    @Override
    public List<Card> findByExpirationMonthAndExpirationYear(int month, int year) {
        return cardRepository.findByExpirationMonthAndExpirationYear(month, year);
    }

    @Override
    public List<Card> getAllCardsUsingJpa() {
        return cardRepository.findAll();
    }
}
