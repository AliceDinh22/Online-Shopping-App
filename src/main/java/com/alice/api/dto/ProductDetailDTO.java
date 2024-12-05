package com.alice.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

  private Long id;

  @NotEmpty(message = "Product detail id must not be empty")
  @NotNull(message = "Product detail id must not be null")
  private String productDetailId;

  @NotEmpty(message = "Name must not be empty")
  @NotNull(message = "Name must not be null")
  private String name;

  @NotNull(message = "Price must not be null")
  private Long price;

  @NotNull(message = "Stock quantity must not be null")
  private Long stockQuantity;
  private Long soldQuantity;
  private Integer status;

  private ProductDTO product;

  private ColorDTO color;

  private SizeDTO size;
}
