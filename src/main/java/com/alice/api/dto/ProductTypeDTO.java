package com.alice.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {

  private Long id;

  @NotEmpty(message = "Product type must not be empty")
  @NotNull(message = "Product type must not be null")
  @Size(min = 2, message = "Product type must be at least 2 characters")
  private String name;
}
