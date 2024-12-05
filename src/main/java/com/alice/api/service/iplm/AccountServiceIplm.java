package com.alice.api.service.iplm;

import com.alice.api.dto.AccountDTO;
import com.alice.api.entity.Account;
import com.alice.api.entity.ShoppingCart;
import com.alice.api.repository.AccountRepository;
import com.alice.api.repository.ShoppingCartRepository;
import com.alice.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceIplm implements AccountService {

  private final AccountRepository accountRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final EmailService emailService;
  private final PasswordEncoder encoder;


  @Override
  public void create(AccountDTO accountDTO) {
    Account account = new Account();
    mapToEntity(account, accountDTO);
    account.setTotalInvoices(0L);
    account.setTotalPrices(0L);
    account.setAccountRank(1);
    account.setStatus(1);
    Account accountSave = accountRepository.save(account);
    ShoppingCart shoppingCart = new ShoppingCart(0L, 0L, accountSave);
    shoppingCartRepository.save(shoppingCart);
    emailService.sendEmail(
        account.getEmail(),
        "Create Account!",
        "Hello " + account.getFullName());
  }


  void mapToEntity(Account account, AccountDTO accountDTO) {
    account.setCustomerId(accountDTO.getCustomerId());
    account.setEmail(accountDTO.getEmail());
    account.setPassword(encoder.encode(accountDTO.getPassword()));
    account.setFullName(accountDTO.getFullName());
    account.setRole(accountDTO.getRole());
  }
}
