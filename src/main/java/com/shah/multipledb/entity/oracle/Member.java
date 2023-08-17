package com.shah.multipledb.entity.oracle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NORUL
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member",schema = "system")
public class Member {
    @Id
    private Integer id;
    private String name;
    private String memberId;
}
