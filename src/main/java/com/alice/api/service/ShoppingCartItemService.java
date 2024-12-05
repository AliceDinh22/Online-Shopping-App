package com.alice.api.service;

import com.alice.api.dto.ShoppingCartItemDTO;
import java.util.List;

public interface ShoppingCartItemService {

  List<ShoppingCartItemDTO> getAll();

  ShoppingCartItemDTO getById(Long id);

  void create(ShoppingCartItemDTO shoppingCartItemDTO);

  void update(ShoppingCartItemDTO shoppingCartItemDTO, Long id);

  void delete(Long id);
}
