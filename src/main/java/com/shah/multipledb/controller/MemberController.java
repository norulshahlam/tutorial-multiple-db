package com.shah.multipledb.controller;


import com.shah.multipledb.entity.oracle.Member;
import com.shah.multipledb.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "get member By Name Using Jpa", tags = "oracle database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("findMemberByNameUsingJpa")
    public ResponseEntity<List<Member>> findMemberByNameUsingJpa(
            @Parameter(description = "Member's name", example = "member2")
            @RequestParam
            @NotBlank
            String name) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(memberService.findMemberByNameUsingJpa(name));
    }

    @Operation(summary = "get all members Using Jpa", tags = "oracle database")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("getAllMembersUsingJpa")
    public ResponseEntity<List<Member>> getAllMembersUsingJpa() {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(memberService.getAllMembersUsingJpa());
    }
}
