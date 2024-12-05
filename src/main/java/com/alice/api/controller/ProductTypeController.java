package com.alice.api.controller;

import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.dto.ResponseDTO;
import com.alice.api.service.ProductTypeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-types")
@RequiredArgsConstructor
public class ProductTypeController {

  private final ProductTypeService productTypeService;

  @GetMapping
  public ResponseDTO<List<ProductTypeDTO>> getAll() {
    return ResponseDTO.<List<ProductTypeDTO>>builder()
        .data(productTypeService.getAll())
        .status(200)
        .message("Get all product types successfully!")
        .build();
  }

  @GetMapping("/{id}")
  public ResponseDTO<ProductTypeDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<ProductTypeDTO>builder()
        .data(productTypeService.getById(id))
        .status(200)
        .message("Get product type by ID successfully!")
        .build();
  }

  @PostMapping
  public ResponseDTO<Void> create(@RequestBody @Valid ProductTypeDTO productTypeDto) {
    productTypeService.create(productTypeDto);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create product type successfully!")
        .build();
  }

  @PutMapping("/{id}")
  public ResponseDTO<Void> update(@PathVariable Long id,
      @RequestBody @Valid ProductTypeDTO productTypeDTO) {
    productTypeService.update(productTypeDTO, id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Update product type by ID successfully!")
        .build();
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<Void> delete(@PathVariable Long id) {
    productTypeService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete product type by ID successfully!")
        .build();
  }
}
