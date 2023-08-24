package com.shah.multipledb.entity.postgres;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NORUL
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "dev")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Schema(type = "string", example = "posb", description = "name of bank on card")
    private String bankName;
    @Schema(type = "int", example = "12")
    private int expirationMonth;
    @Schema(type = "int", example = "2000")
    private int expirationYear;
}
