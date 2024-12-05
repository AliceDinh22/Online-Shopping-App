package com.alice.api.service.iplm;

import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.entity.ProductType;
import com.alice.api.repository.ProductTypeRepository;
import com.alice.api.service.ProductTypeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceIplm implements ProductTypeService {

  private final ProductTypeRepository productTypeRepository;

  @Override
  public List<ProductTypeDTO> getAll() {
    return mapToDto(productTypeRepository.findAll());
  }

  @Override
  public ProductTypeDTO getById(Long id) {
    ProductType productType = productTypeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new ProductTypeDTO(productType.getId(), productType.getName());
  }

  @Override
  public void create(ProductTypeDTO productTypeDTO) {
    ProductType productType = new ProductType();
    productType.setName(productTypeDTO.getName());
    productTypeRepository.save(productType);
  }

  @Override
  public void update(ProductTypeDTO productTypeDTO, Long id) {
    if (!productTypeRepository.existsById(id)) {
      throw new EntityNotFoundException("Not found!");
    }

    productTypeRepository.save(new ProductType(id, productTypeDTO.getName()));
  }

  @Override
  public void delete(Long id) {
    if (!productTypeRepository.existsById(id)) {
      throw new EntityNotFoundException("Not found!");
    }

    productTypeRepository.deleteById(id);
  }

  private List<ProductTypeDTO> mapToDto(List<ProductType> product) {
    List<ProductTypeDTO> result = new ArrayList<>();

    for (ProductType p : product) {
      result.add(new ProductTypeDTO(p.getId(), p.getName()));
    }

    return result;
  }
}
