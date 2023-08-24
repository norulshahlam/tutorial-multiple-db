package com.shah.multipledb.repository.postgres;

import com.shah.multipledb.entity.postgres.Card;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NORUL
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

     /**
     * Uses JPA to retrieve
     *
     * @param name name of cardholder
     * @return list of cards
     */
    List<Card> findByBankName(String name);

    /**
     * Uses JPQL to retrieve
     *
     * @param month expirationMonth
     * @param year  expirationYear
     * @return list of cards
     */
    @Query("select c from Card c where c.expirationMonth = :month " +
            "and c.expirationYear = :year ")
    List<Card> findByExpirationMonthAndExpirationYearUsingJpql(int month, int year);

    @Query(value = "select * from account where type = :accountType",
    nativeQuery = true)
    List<Tuple> findAccountByAccountTypeUsingNativeSql(String accountType);
}
