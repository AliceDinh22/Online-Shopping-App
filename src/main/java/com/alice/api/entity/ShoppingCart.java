package com.alice.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long totalProduct;
  private Long totalPrice;

  @OneToOne
  @JoinColumn(name = "account_id")
  private Account account;

  public ShoppingCart(Long totalProduct, Long totalPrice, Account account) {
    this.totalProduct = totalProduct;
    this.totalPrice = totalPrice;
    this.account = account;
  }
}
