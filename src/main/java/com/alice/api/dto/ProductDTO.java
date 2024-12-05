package com.alice.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

  private Long id;

  @NotEmpty(message = "Product id must not be empty")
  @NotNull(message = "Product id must not be null")
  private String productId;

  @NotEmpty(message = "Name must not be empty")
  @NotNull(message = "Name must not be null")
  private String name;

  @NotNull(message = "Price must not be null")
  private Long price;

  @NotNull(message = "Stock quantity must not be null")
  private Long stockQuantity;

  private Long soldQuantity;
  private String description;
  private Integer status;

  private ProductTypeDTO productType;

  private List<String> images;

  @JsonIgnore
  private List<MultipartFile> files; // User uploaded files
}
