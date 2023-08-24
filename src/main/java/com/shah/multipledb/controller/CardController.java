package com.shah.multipledb.controller;

import com.shah.multipledb.entity.postgres.Account;
import com.shah.multipledb.entity.postgres.Card;
import com.shah.multipledb.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author NORUL
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Get all cards using Jpa", tags = "postgres database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("getAllCardsUsingJpa")
    public ResponseEntity<List<Card>> getAllCardsUsingJpa() {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardService.getAllCardsUsingJpa());
    }

    @Operation(summary = "Find cards by bank name using Jpa", tags = "postgres database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("findCardsByNameUsingJpa")
    public ResponseEntity<List<Card>> findCardsByNameUsingJpa(
            @Parameter(description = "name of bank on card", example = "dbs")
            @RequestParam
            @NotBlank
            String name) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardService.findByBankName(name));
    }

    @Operation(summary = "Find Cards By expiration month and expiration year using Jpql", tags = "postgres database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("findByExpirationMonthAndExpirationYearUsingJpql")
    public ResponseEntity<List<Card>> findByExpirationMonthAndExpirationYear(
            @Parameter(description = "Month of expiration", example = "12")
            @RequestParam
            @NotBlank
            int expirationMonth,
            @Parameter(description = "Year of expiration", example = "2000")
            @RequestParam
            @NotBlank
            int expirationYear) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardService.findByExpirationMonthAndExpirationYearUsingJpql(expirationMonth, expirationYear));
    }

    @Operation(summary = "Find account by account type using native Sql. Returns Tuple", tags = "postgres database with Tuple handler")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("findAccountByAccountTypeUsingNativeSql")
    public ResponseEntity<List<Account>> findAccountByAccountTypeUsingNativeSql(
            @Parameter(description = "Type of account", example = "loan")
            @RequestParam
            @NotBlank
            String accountType) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardService.findAccountByAccountTypeUsingNativeSql(accountType));
    }
}
