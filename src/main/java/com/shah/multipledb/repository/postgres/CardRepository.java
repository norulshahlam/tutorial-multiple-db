package com.shah.multipledb.repository.postgres;

import com.shah.multipledb.entity.postgres.Card;
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
     * @param name name of cardholder
     * @return list of cards
     */
    List<Card> findByName(String name);

    /**
     * Uses JPQL to retrieve
     * @param month expirationMonth
     * @param year expirationYear
     * @return list of cards
     */
    @Query("select c from Card c where c.expirationMonth = :month " +
            "and c.expirationYear = :year ")
    List<Card> findByExpirationMonthAndExpirationYear(int month, int year);
}
