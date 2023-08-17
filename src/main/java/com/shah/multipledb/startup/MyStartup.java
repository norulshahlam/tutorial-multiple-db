package com.shah.multipledb.startup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shah.multipledb.entity.mysql.CardHolder;
import com.shah.multipledb.entity.oracle.Member;
import com.shah.multipledb.entity.postgres.Card;
import com.shah.multipledb.repository.mysql.CardHolderRepository;
import com.shah.multipledb.repository.oracle.MemberRepository;
import com.shah.multipledb.repository.postgres.CardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author NORUL
 */
@Component
@Slf4j
public class MyStartup implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext(unitName = "mysqlManagerFactory")
    private EntityManager entityManager;

    @Autowired
    private Environment environment;

    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public void run(String... args) throws Exception {
        log.info("Application just loaded! Saving card...");
        List<Card> cards = cardRepository.saveAll(
                Stream.of(
                        new Card(null, "posb", 12, 2000),
                        new Card(null, "rhb", 12, 2000),
                        new Card(null, "dbs", 4, 2029),
                        new Card(null, "rbs", 7, 2024)
                ).toList());
        log.info("cards saved: \n {}", ow.writeValueAsString(cards));


        List<CardHolder> cardHolders = cardHolderRepository.saveAll(
                Stream.of(
                        new CardHolder(null, "adam", 2346745),
                        new CardHolder(null, "adam", 468),
                        new CardHolder(null, "Bob", 3634637),
                        new CardHolder(null, "jeff", 4567747)
                ).toList());

        log.info("cardholders saved: \n {}", ow.writeValueAsString(cardHolders));

        List<Member> memberList = memberRepository.saveAll(
                Stream.of(
                        new Member(1, "member1", "2456"),
                        new Member(2, "member2", "7986"),
                        new Member(3, "member3", "457"),
                        new Member(4, "member2", "1092")
                ).toList());

        log.info("memberList saved: \n {}", ow.writeValueAsString(memberList));
    }
}
