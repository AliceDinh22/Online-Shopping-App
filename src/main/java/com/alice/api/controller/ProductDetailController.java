package com.alice.api.controller;

import com.alice.api.dto.ProductDetailDTO;
import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.dto.ResponseDTO;
import com.alice.api.service.ProductDetailService;
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
@RequestMapping("/product-details")
@RequiredArgsConstructor
public class ProductDetailController {

  private final ProductDetailService productDetailService;

  @GetMapping
  public ResponseDTO<List<ProductDetailDTO>> getAll() {
    return ResponseDTO.<List<ProductDetailDTO>>builder()
        .data(productDetailService.getAll())
        .status(200)
        .message("Get all product detail successfully!")
        .build();
  }

  @GetMapping("/{id}")
  public ResponseDTO<ProductDetailDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<ProductDetailDTO>builder()
        .data(productDetailService.getById(id))
        .status(200)
        .message("Get product detail by id successfully!")
        .build();
  }

  @PostMapping
  public ResponseDTO<Void> create(@RequestBody @Valid ProductDetailDTO productDetailDTO) {
    productDetailService.create(productDetailDTO);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create product detail successfully!")
        .build();
  }

  @PutMapping("/{id}")
  public ResponseDTO<Void> update(@PathVariable Long id,
      @RequestBody ProductDetailDTO ProductDetailDTO) {
    productDetailService.update(ProductDetailDTO, id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Update product detail by ID successfully!")
        .build();
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<Void> delete(@PathVariable Long id) {
    productDetailService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete product detail by ID successfully!")
        .build();
  }
}
