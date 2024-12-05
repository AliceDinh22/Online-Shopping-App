package com.alice.api.controller;

import com.alice.api.dto.ProductDTO;
import com.alice.api.dto.ResponseDTO;
import com.alice.api.service.ProductService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseDTO<List<ProductDTO>> getAll() {
    return ResponseDTO.<List<ProductDTO>>builder()
        .data(productService.getAll())
        .status(200)
        .message("Get all products successfully!")
        .build();
  }

  @GetMapping("/{id}")
  public ResponseDTO<ProductDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<ProductDTO>builder()
        .data(productService.getById(id))
        .status(200)
        .message("Get product by id successfully!")
        .build();
  }

  @GetMapping("/search")
  public ResponseDTO<List<ProductDTO>> search(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "productId", required = false) String productId,
      @RequestParam(value = "productTypeId", required = false) Long productTypeId,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size) {
    return ResponseDTO.<List<ProductDTO>>builder()
        .data(productService.search(name, productId, productTypeId, page, size))
        .status(200)
        .message("Search product successfully!")
        .build();
  }

  @PostMapping
  public ResponseDTO<Void> create(@ModelAttribute @Valid ProductDTO productDTO) throws IOException {
    productService.create(productDTO);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create product successfully!")
        .build();
  }

  @PutMapping("/{id}")
  public ResponseDTO<Void> update(
      @PathVariable Long id,
      @ModelAttribute @Valid ProductDTO productDTO) throws IOException {
    productService.update(productDTO, id);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Update product successfully!")
        .build();
  }

  @DeleteMapping("/{id}")
  public ResponseDTO<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete product by id successfully!")
        .build();
  }
}
