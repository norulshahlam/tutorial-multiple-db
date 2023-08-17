package com.shah.multipledb.entity.postgres;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Schema(type = "string", example = "posb", description = "name of bank on card")
    private String name;
    @Schema(type = "int", example = "12")
    private int expirationMonth;
    @Schema(type = "int", example = "2000")
    private int expirationYear;
}
