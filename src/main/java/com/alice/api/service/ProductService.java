package com.alice.api.service;

import com.alice.api.dto.ProductDTO;
import java.io.IOException;
import java.util.List;

public interface ProductService {

  List<ProductDTO> getAll();

  List<ProductDTO> search(String name, String productId, Long productTypeId, Integer page,
      Integer size);

  ProductDTO getById(Long id);

  void create(ProductDTO productDTO) throws IOException;

  void update(ProductDTO productDTO, Long id) throws IOException;

  void delete(Long id);
}
