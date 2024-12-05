package com.alice.api.service;

import com.alice.api.dto.ProductDetailDTO;
import java.util.List;

public interface ProductDetailService {

  List<ProductDetailDTO> getAll();

  ProductDetailDTO getById(Long id);

  void create(ProductDetailDTO productDetailDTO);

  void update(ProductDetailDTO productDetailDTO, Long id);

  void delete(Long id);
}
