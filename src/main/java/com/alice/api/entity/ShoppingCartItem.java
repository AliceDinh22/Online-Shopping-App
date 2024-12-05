package com.alice.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long quantity;
  private Long totalPrice;

  @ManyToOne
  @JoinColumn(name = "product_detail_id")
  private ProductDetail productDetail;

  @ManyToOne
  @JoinColumn(name = "shopping_cart_id")
  private ShoppingCart shoppingCart;
}
