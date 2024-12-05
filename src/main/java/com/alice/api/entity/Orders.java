package com.alice.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;
}
