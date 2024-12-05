package com.alice.api.service.iplm;

import com.alice.api.dto.ColorDTO;
import com.alice.api.dto.ProductDTO;
import com.alice.api.dto.ProductDetailDTO;
import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.dto.SizeDTO;
import com.alice.api.entity.ProductDetail;
import com.alice.api.repository.ColorRepository;
import com.alice.api.repository.ProductDetailRepository;
import com.alice.api.repository.ProductRepository;
import com.alice.api.repository.SizeRepository;
import com.alice.api.service.ProductDetailService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceIplm implements ProductDetailService {

  private final ProductDetailRepository productDetailRepository;
  private final ProductRepository productRepository;
  private final ColorRepository colorRepository;
  private final SizeRepository sizeRepository;

  @Override
  public List<ProductDetailDTO> getAll() {
    return mapToDto(productDetailRepository.findAll());
  }

  @Override
  public ProductDetailDTO getById(Long id) {
    ProductDetail product = productDetailRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new ProductDetailDTO(
        product.getId(),
        product.getProductDetailId(),
        product.getName(),
        product.getPrice(),
        product.getStockQuantity(),
        product.getSoldQuantity(),
        product.getStatus(),
        new ProductDTO(
            product.getProduct().getId(),
            product.getProduct().getProductId(),
            product.getProduct().getName(),
            product.getProduct().getPrice(),
            product.getProduct().getStockQuantity(),
            product.getProduct().getSoldQuantity(),
            product.getProduct().getDescription(),
            product.getProduct().getStatus(),
            new ProductTypeDTO(
                product.getProduct().getProductType().getId(),
                product.getProduct().getProductType().getName()),
            product.getProduct().getImages(), null),
        new ColorDTO(product.getColor().getId(), product.getColor().getName()),
        new SizeDTO(product.getSize().getId(), product.getSize().getName())
    );
  }

  @Override
  public void create(ProductDetailDTO productDetailDTO) {
    ProductDetail productDetail = new ProductDetail();
    mapToEntity(productDetail, productDetailDTO);
    productDetailRepository.save(productDetail);
  }

  @Override
  public void update(ProductDetailDTO productDetailDTO, Long id) {
    ProductDetail productDetail = productDetailRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));
    mapToEntity(productDetail, productDetailDTO);
    productDetailRepository.save(productDetail);
  }

  @Override
  public void delete(Long id) {
    if (!productDetailRepository.existsById(id)) {
      throw new EntityNotFoundException("Not found!");
    }

    productDetailRepository.deleteById(id);
  }

  private List<ProductDetailDTO> mapToDto(List<ProductDetail> productDetail) {
    List<ProductDetailDTO> result = new ArrayList<>();

    for (ProductDetail p : productDetail) {
      ProductDetailDTO dto = new ProductDetailDTO();

      dto.setId(p.getId());
      dto.setProductDetailId(p.getProductDetailId());
      dto.setName(p.getName());
      dto.setPrice(p.getPrice());
      dto.setStockQuantity(p.getStockQuantity());
      dto.setSoldQuantity(p.getSoldQuantity());
      dto.setStatus(p.getStatus());
      dto.setProduct(new ProductDTO(
          p.getProduct().getId(),
          p.getProduct().getProductId(),
          p.getProduct().getName(),
          p.getProduct().getPrice(),
          p.getProduct().getStockQuantity(),
          p.getProduct().getSoldQuantity(),
          p.getProduct().getDescription(),
          p.getProduct().getStatus(),
          new ProductTypeDTO(
              p.getProduct().getProductType().getId(),
              p.getProduct().getProductType().getName()),
          p.getProduct().getImages(), null));
      dto.setColor(new ColorDTO(p.getColor().getId(), p.getColor().getName()));
      dto.setSize(new SizeDTO(p.getSize().getId(), p.getSize().getName()));

      result.add(dto);
    }
    return result;
  }

  private void mapToEntity(ProductDetail product, ProductDetailDTO dto) {
    product.setProductDetailId(dto.getProductDetailId());
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    product.setStockQuantity(dto.getStockQuantity());
    product.setStatus(dto.getStatus());
    product.setProduct(productRepository.findById(dto.getProduct().getId())
        .orElseThrow(() -> new EntityNotFoundException("Not found!")));
    product.setColor(colorRepository.findById(dto.getColor().getId())
        .orElseThrow(() -> new EntityNotFoundException("Not found!")));
    product.setSize(sizeRepository.findById(dto.getSize().getId())
        .orElseThrow(() -> new EntityNotFoundException("Not found!")));
  }
}
