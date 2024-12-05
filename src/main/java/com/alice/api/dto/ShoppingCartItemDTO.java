package com.alice.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemDTO {

  private Long id;
  private Long quantity;
  private Long totalPrice;

  private ProductDetailDTO productDetail;

  private Long shoppingCartId;
}
