package com.alice.api.repository;

import com.alice.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(""" 
      SELECT p FROM Product p 
      WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name,'%')) 
      AND (:productId IS NULL OR p.productId LIKE CONCAT('%', :productId,'%'))
      AND (:productTypeId IS NULL OR p.productType.id = :productTypeId)
      """)
  Page<Product> getAll(
      @Param("name") String name,
      @Param("productId") String productId,
      @Param("productTypeId") Long productTypeId,
      Pageable pageable);
}
