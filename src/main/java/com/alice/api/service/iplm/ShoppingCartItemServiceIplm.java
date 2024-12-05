package com.alice.api.service.iplm;

import com.alice.api.dto.ColorDTO;
import com.alice.api.dto.ProductDTO;
import com.alice.api.dto.ProductDetailDTO;
import com.alice.api.dto.ProductTypeDTO;
import com.alice.api.dto.ShoppingCartItemDTO;
import com.alice.api.dto.SizeDTO;
import com.alice.api.entity.ProductDetail;
import com.alice.api.entity.ShoppingCart;
import com.alice.api.entity.ShoppingCartItem;
import com.alice.api.repository.ProductDetailRepository;
import com.alice.api.repository.ShoppingCartItemRepository;
import com.alice.api.repository.ShoppingCartRepository;
import com.alice.api.service.ShoppingCartItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemServiceIplm implements ShoppingCartItemService {

  private final ShoppingCartItemRepository shoppingCartItemRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final ProductDetailRepository productDetailRepository;


  @Override
  public List<ShoppingCartItemDTO> getAll() {
    return mapToDTO(shoppingCartItemRepository.findAll());
  }

  @Override
  public ShoppingCartItemDTO getById(Long id) {
    ShoppingCartItem s = shoppingCartItemRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new ShoppingCartItemDTO(
        s.getId(),
        s.getQuantity(),
        s.getTotalPrice(),
        setToProductDetailDTO(s),
        s.getShoppingCart().getId()
    );
  }

  @Override
  public void create(ShoppingCartItemDTO shoppingCartItemDTO) {
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
    mapToEntity(shoppingCartItem, shoppingCartItemDTO);
    shoppingCartItemRepository.save(shoppingCartItem);
  }

  @Override
  public void update(ShoppingCartItemDTO shoppingCartItemDTO, Long id) {
    ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    mapToEntity(shoppingCartItem, shoppingCartItemDTO);
    shoppingCartItemRepository.save(shoppingCartItem);
  }

  @Override
  public void delete(Long id) {
    if (!shoppingCartItemRepository.existsById(id))
      throw new EntityNotFoundException("Not found!");

    shoppingCartItemRepository.deleteById(id);
  }

  private List<ShoppingCartItemDTO> mapToDTO(List<ShoppingCartItem> shoppingCartItems) {
    List<ShoppingCartItemDTO> result = new ArrayList<>();

    if (shoppingCartItems == null) {
      return result;
    }

    for (ShoppingCartItem s : shoppingCartItems) {
      ShoppingCartItemDTO dto = new ShoppingCartItemDTO();

      dto.setId(s.getId());
      dto.setQuantity(s.getQuantity());
      dto.setTotalPrice(s.getTotalPrice());
      dto.setProductDetail(setToProductDetailDTO(s));
      dto.setShoppingCartId(s.getShoppingCart().getId());

      result.add(dto);
    }
    return result;
  }

  private ProductDetailDTO setToProductDetailDTO(ShoppingCartItem s) {
    return new ProductDetailDTO(
        s.getProductDetail().getId(),
        s.getProductDetail().getProductDetailId(),
        s.getProductDetail().getName(),
        s.getProductDetail().getPrice(),
        s.getProductDetail().getStockQuantity(),
        s.getProductDetail().getSoldQuantity(),
        s.getProductDetail().getStatus(),

        new ProductDTO(
            s.getProductDetail().getProduct().getId(),
            s.getProductDetail().getProduct().getProductId(),
            s.getProductDetail().getProduct().getName(),
            s.getProductDetail().getProduct().getPrice(),
            s.getProductDetail().getProduct().getStockQuantity(),
            s.getProductDetail().getProduct().getSoldQuantity(),
            s.getProductDetail().getProduct().getDescription(),
            s.getProductDetail().getProduct().getStatus(),
            new ProductTypeDTO(
                s.getProductDetail().getProduct().getProductType().getId(),
                s.getProductDetail().getProduct().getProductType().getName()),
            s.getProductDetail().getProduct().getImages(),
            null),

        new ColorDTO(
            s.getProductDetail().getColor().getId(),
            s.getProductDetail().getColor().getName()),

        new SizeDTO(
            s.getProductDetail().getSize().getId(),
            s.getProductDetail().getSize().getName())
    );
  }

  private void mapToEntity(ShoppingCartItem entity, ShoppingCartItemDTO dto) {
    ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetail().getId())
        .orElseThrow(() -> new EntityNotFoundException("ProductDetail not found!"));

    entity.setProductDetail(productDetail);

    ShoppingCart shoppingCart = shoppingCartRepository.findById(dto.getShoppingCartId())
        .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found!"));

    entity.setShoppingCart(shoppingCart);

    Long totalPrice = productDetail.getPrice() * dto.getQuantity();

    // After adding the product plus price
    shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() + totalPrice);

    ShoppingCartItem shoppingCartItem = shoppingCartItemRepository
        .findByShoppingCartIdAndProductDetailId(dto.getShoppingCartId(), productDetail.getId())
        .orElse(null);

    if (shoppingCartItem != null) {
      entity.setId(shoppingCartItem.getId());
      entity.setQuantity(shoppingCartItem.getQuantity() + dto.getQuantity());
      entity.setTotalPrice(shoppingCartItem.getTotalPrice() + totalPrice);
    } else {
      entity.setQuantity(dto.getQuantity());
      entity.setTotalPrice(totalPrice);
      shoppingCart.setTotalProduct(shoppingCart.getTotalProduct() + 1);
    }
  }
}
