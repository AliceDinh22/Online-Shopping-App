package com.alice.api.repository;

import com.alice.api.entity.ShoppingCartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

  // select * from shoppng_cart_item where shopping_cart_id = ? and product_detail_id = ?
  Optional<ShoppingCartItem> findByShoppingCartIdAndProductDetailId(Long shoppingCartId, Long id);
}
