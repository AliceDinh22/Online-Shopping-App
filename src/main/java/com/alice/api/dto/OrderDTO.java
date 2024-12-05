package com.alice.api.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  private Long id;
  private String orderId;
  private Long totalProduct;
  private Long totalPrice;

  // Order
  private String fullName;
  private String phoneNumber;
  private String address;

  private String staffId; // Save information when selling at the counter
  private LocalDate createdDate; // Online: start date
  private LocalDate completedDate; // Online: the date when the customer receives the goods
  private String canceledReason;
  private Integer status; //0: Canceled - 1: Waiting - 2: Waiting for pickup - 3: Delivering - 4: Completed
  private Integer orderType; //1: Online - 2: Counter
  private Long accountId;
}
