package com.shah.multipledb.repository.mysql;

import com.shah.multipledb.entity.mysql.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NORUL
 */
@Repository
public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {

    List<CardHolder> findByMemberName(String memberId);
}
