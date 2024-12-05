package com.alice.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {

  private Long id;
  @NotEmpty(message = "Color must not be empty")
  @NotNull(message = "Color must not be null")
  private String name;
}
