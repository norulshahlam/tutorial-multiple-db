package com.shah.multipledb.repository.oracle;

import com.shah.multipledb.entity.oracle.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NORUL
 */
@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    List<Member> findByName(String name);

}
