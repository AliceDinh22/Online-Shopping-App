package com.alice.api.service.iplm;

import com.alice.api.dto.ProductDTO;
import com.alice.api.entity.Product;
import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.repository.ProductRepository;
import com.alice.api.repository.ProductTypeRepository;
import com.alice.api.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceIplm implements ProductService {

  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;

  @Override
  public List<ProductDTO> getAll() {
    return mapToDto(productRepository.findAll());
  }

  @Override
  public List<ProductDTO> search(String name, String productId, Long productTypeId, Integer page,
      Integer size) {
    page = page == null ? 0 : page;
    size = size == null ? 2 : size;

    Page<Product> pageResult = productRepository.getAll(name, productId, productTypeId,
        PageRequest.of(page, size));

    return mapToDto(pageResult.getContent());
  }

  @Override
  public ProductDTO getById(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new ProductDTO(
        product.getId(),
        product.getProductId(),
        product.getName(),
        product.getPrice(),
        product.getStockQuantity(),
        product.getSoldQuantity(),
        product.getDescription(),
        product.getStatus(),
        new ProductTypeDTO(
            product.getProductType().getId(),
            product.getProductType().getName()),
        product.getImages(),
        null);
  }

  @Override
  public void create(ProductDTO productDTO) throws IOException {
    List<String> images = new ArrayList<>();

    if (productDTO.getFiles() != null) {
      handleFile(productDTO, images);
    } else {
      throw new EntityNotFoundException("Please select a photo!");
    }

    Product product = new Product();
    mapToEntity(product, productDTO);
    product.setImages(images);
    product.setSoldQuantity(0L);
    product.setStatus(1);
    productRepository.save(product);
  }

  @Override
  public void update(ProductDTO productDTO, Long id) throws IOException {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    List<String> images = new ArrayList<>();

    if (productDTO.getFiles() != null) {
      handleFile(productDTO, images);
      product.setImages(images);
    }

    mapToEntity(product, productDTO);
    product.setStatus(productDTO.getStatus());
    productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new EntityNotFoundException("Id not found!");
    }

    productRepository.deleteById(id);
  }

  private List<ProductDTO> mapToDto(List<Product> listEntity) {
    List<ProductDTO> list = new ArrayList<>();

    for (Product entity : listEntity) {
      ProductDTO productDTO = new ProductDTO();

      productDTO.setId(entity.getId());
      productDTO.setProductId(entity.getProductId());
      productDTO.setName(entity.getName());
      productDTO.setPrice(entity.getPrice());
      productDTO.setStockQuantity(entity.getStockQuantity());
      productDTO.setSoldQuantity(entity.getSoldQuantity());
      productDTO.setDescription(entity.getDescription());
      productDTO.setStatus(entity.getStatus());
      productDTO.setProductType(
          new ProductTypeDTO(
              entity.getProductType().getId(),
              entity.getProductType().getName()));
      productDTO.setImages(entity.getImages());

      list.add(productDTO);
    }
    return list;
  }

  private void handleFile(ProductDTO productDTO, List<String> images) throws IOException {
    for (MultipartFile file : productDTO.getFiles()) {
      String name = file.getOriginalFilename();
      images.add(name);
      String path = "D:/Code/Image";
      File folder = new File(path);

      if (!folder.exists()) {
        folder.mkdirs();
      }

      file.transferTo(new File(path + "/" + name));
    }
  }

  private void mapToEntity(Product product, ProductDTO productDTO) {
    product.setProductId(productDTO.getProductId());
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setStockQuantity(productDTO.getStockQuantity());
    product.setDescription(productDTO.getDescription());
    product.setProductType(productTypeRepository.findById(productDTO.getProductType().getId())
        .orElseThrow(() -> new EntityNotFoundException("Product type not found!")));
  }
}
