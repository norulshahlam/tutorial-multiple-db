package com.shah.multipledb.impl;

import com.shah.multipledb.entity.postgres.Account;
import com.shah.multipledb.entity.postgres.Card;
import com.shah.multipledb.repository.postgres.CardRepository;
import com.shah.multipledb.service.CardService;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author NORUL
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> findByBankName(String name) {
        return cardRepository.findByBankName(name);
    }

    @Override
    public List<Card> findByExpirationMonthAndExpirationYearUsingJpql(int month, int year) {
        return cardRepository.findByExpirationMonthAndExpirationYearUsingJpql(month, year);
    }

    @Override
    public List<Card> getAllCardsUsingJpa() {
        return cardRepository.findAll();
    }

    @Override
    public List<Account> findAccountByAccountTypeUsingNativeSql(String accountType) {
        List<Tuple> tupleList = cardRepository.findAccountByAccountTypeUsingNativeSql(accountType);

        return tupleToListMapper(tupleList);
    }

    private List<Account> tupleToListMapper(List<Tuple> tupleList) {
        log.info("inside tupleToListMapper");
        return tupleList.parallelStream()
                .filter(ObjectUtils::isNotEmpty)
                .map(i -> Account.builder()
                        .id(i.get("id", Long.class))
                        .accountNumber(i.get("accountNumber", String.class))
                        .type(i.get("type", String.class))
                        .activatedDate(i.get("activatedDate", Date.class))
                        .build())
                .toList();
    }
}
