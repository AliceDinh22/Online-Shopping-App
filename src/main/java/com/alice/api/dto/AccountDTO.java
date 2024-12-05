package com.alice.api.dto;

import com.alice.api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

  private Long id;
  private String customerId;
  private String email;
  private String password;
  private String fullName;
  private Role role;
  private Long totalInvoices;
  private Long totalPrices;

  // 1: normal: auto
  // 2: VIP: totalInvoices > 75 and totalPrices > 15
  private Integer accountRank; // 1: normal - 2: VIP

  private Integer status;
}
