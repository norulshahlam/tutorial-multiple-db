package com.shah.multipledb.service;

import com.shah.multipledb.entity.postgres.Account;
import com.shah.multipledb.entity.postgres.Card;

import java.util.List;

/**
 * @author NORUL
 */
public interface CardService {
    /**
     * Returns the card
     * @param name name of cardholder
     * @return list of cards
     */
    List<Card> findByBankName(String name);

    /**
     * Returns the card
     * @param month expirationMonth
     * @param year expirationYear
     * @return list of cards
     */
    List<Card> findByExpirationMonthAndExpirationYearUsingJpql(int month, int year);

    List<Card> getAllCardsUsingJpa();

    List<Account> findAccountByAccountTypeUsingNativeSql(String accountType);
}
