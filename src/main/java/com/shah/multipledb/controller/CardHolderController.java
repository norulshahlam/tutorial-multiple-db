package com.shah.multipledb.controller;


import com.shah.multipledb.entity.mysql.CardHolder;
import com.shah.multipledb.service.CardHolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CardHolderController {

    private final CardHolderService cardHolderService;

    @Operation(summary = "get cardholders By Name Using Jpa", tags = "mysql database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("findCardHolderByMemberNameUsingJpa")
    public ResponseEntity<List<CardHolder>> findCardHolderByMemberNameUsingJpa(
            @Parameter(description = "Member card's name", example = "adam")
            @RequestParam
            @NotBlank
            String name) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardHolderService.findByMemberId(name));
    }

    @Operation(summary = "get all cardholders Using Jpa", tags = "mysql database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("getAllCardholdersUsingJpa")
    public ResponseEntity<List<CardHolder>> getAllCardholdersUsingJpa() {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(cardHolderService.getAllCardholdersUsingJpa());
    }
}
