package com.alice.api.controller;

import com.alice.api.dto.ResponseDTO;
import com.alice.api.dto.ShoppingCartItemDTO;
import com.alice.api.service.ShoppingCartItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping-cart-items")
@RequiredArgsConstructor
public class ShoppingCartItemController {

  private final ShoppingCartItemService shoppingCartItemService;

  @GetMapping
  private ResponseDTO<List<ShoppingCartItemDTO>> getAll() {
    return ResponseDTO.<List<ShoppingCartItemDTO>>builder()
        .data(shoppingCartItemService.getAll())
        .status(200)
        .message("Get all shopping cart items successfully!")
        .build();
  }

  @GetMapping("/{id}")
  private ResponseDTO<ShoppingCartItemDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<ShoppingCartItemDTO>builder()
        .data(shoppingCartItemService.getById(id))
        .status(200)
        .message("Get shopping cart item by id successfully!")
        .build();
  }

  @PostMapping
  private ResponseDTO<Void> crete(@RequestBody ShoppingCartItemDTO shoppingCartItemDTO) {
    shoppingCartItemService.create(shoppingCartItemDTO);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create shopping cart item successfully!")
        .build();
  }

  @PutMapping("/{id}")
  private ResponseDTO<Void> update(
      @RequestBody ShoppingCartItemDTO shoppingCartItemDTO,
      @PathVariable Long id) {
    shoppingCartItemService.update(shoppingCartItemDTO, id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Update shopping cart item by id successfully!")
        .build();
  }

  @DeleteMapping("/{id}")
  private ResponseDTO<Void> delete(@PathVariable Long id) {
    shoppingCartItemService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete shopping cart item by id successfully!")
        .build();
  }
}
