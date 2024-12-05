package com.alice.api.service.iplm;

import com.alice.api.entity.Account;
import com.alice.api.repository.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

  private final AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Account> userDetail = repository.findByEmail(username);
    return userDetail.orElseThrow(() -> new RuntimeException("User not found: " + username));
  }
}
