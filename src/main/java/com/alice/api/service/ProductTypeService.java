package com.alice.api.service;

import com.alice.api.dto.ProductTypeDTO;
import java.util.List;

public interface ProductTypeService {

  List<ProductTypeDTO> getAll();

  ProductTypeDTO getById(Long id);

  void create(ProductTypeDTO productTypeDTO);

  void update(ProductTypeDTO productTypeDTO, Long id);

  void delete(Long id);
}
