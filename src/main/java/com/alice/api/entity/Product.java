package com.alice.api.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String productId;
  private String name;
  private Long price;
  private Long stockQuantity;
  private Long soldQuantity;
  private String description;
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "product_type_id", referencedColumnName = "id")
  private ProductType productType;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "product_image")
  private List<String> images;
}
