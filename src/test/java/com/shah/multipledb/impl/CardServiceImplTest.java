package com.shah.multipledb.impl;

import com.shah.multipledb.entity.postgres.Account;
import com.shah.multipledb.entity.postgres.Card;
import com.shah.multipledb.repository.postgres.CardRepository;
import jakarta.persistence.Tuple;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hibernate.jpa.spi.NativeQueryTupleTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    private NativeQueryTupleTransformer tupleTransformer = new NativeQueryTupleTransformer();

    private List<Tuple> tupleList = new ArrayList<>();
    private List<Card> cardList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        cardList = Stream.of(
                new Card(1L, "posb", 12, 2000),
                new Card(2L, "rhb", 12, 2000),
                new Card(3L, "dbs", 4, 2029),
                new Card(4L, "rbs", 7, 2024)
        ).toList();

        List<Account> accounts = Arrays.asList(
                new Account(1L, "456-768", "loan",
                        new Date(2022, Calendar.DECEMBER, 20)),
                new Account(4L, "245-397", "loan",
                        new Date(2022, Calendar.NOVEMBER, 15)));

        accounts.forEach(i -> tupleList.add(tupleTransformer.transformTuple(
                new Object[]{i.getId(), i.getAccountNumber(), i.getType(), i.getActivatedDate()},
                new String[]{"id", "accountNumber", "type", "activatedDate"}
        )));
    }

    @Test
    void findByBankName() {
        when(cardRepository.findByBankName("dbs")).thenReturn(cardList);
        List<Card> dbs = cardService.findByBankName("dbs");

        /* Since our list contains not only dbs, we use this method to check if  - not recommended */
        MatcherAssert.assertThat(dbs, hasItem(Matchers.hasProperty("bankName", equalTo("dbs"))));
    }

    @Test
    void findByExpirationMonthAndExpirationYearUsingJpql() {
        cardList = Stream.of(
                new Card(1L, "posb", 12, 2000),
                new Card(2L, "rhb", 12, 2000)
        ).toList();

        when(cardRepository.findByExpirationMonthAndExpirationYearUsingJpql(12, 2000))
                .thenReturn(cardList);
        List<Card> list = cardService.findByExpirationMonthAndExpirationYearUsingJpql(12, 2000);
        list.forEach(i -> {
            assertThat(i.getExpirationMonth()).isEqualTo(12);
            assertThat(i.getExpirationYear()).isEqualTo(2000);
        });
    }

    @Test
    void getAllCardsUsingJpa() {
        when(cardRepository.findAll()).thenReturn(cardList);
        List<Card> list = cardService.getAllCardsUsingJpa();
        assertThat(list).isNotNull();
    }

    @Test
    void findAccountByAccountTypeUsingNativeSql() {
        when(cardRepository.findAccountByAccountTypeUsingNativeSql("loan"))
                .thenReturn(tupleList);
        List<Account> loan = cardService.findAccountByAccountTypeUsingNativeSql("loan");
        loan.forEach(i ->
                assertThat(i.getType()).isEqualTo("loan"));
    }
}