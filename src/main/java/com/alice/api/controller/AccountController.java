package com.alice.api.controller;

import com.alice.api.dto.AccountDTO;
import com.alice.api.dto.ResponseDTO;
import com.alice.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  public ResponseDTO<String> create(@RequestBody AccountDTO accountDTO) {
    accountService.create(accountDTO);
    return ResponseDTO.<String>builder()
        .status(201)
        .message("Create account successfully!")
        .build();
  }

}
