package com.alice.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {

  private Long id;

  @NotEmpty(message = "Color must not be empty")
  @NotNull(message = "Color must not be null")
  @Size(min = 2, message = "Color must be at least 2 characters")
  private String name;
}
