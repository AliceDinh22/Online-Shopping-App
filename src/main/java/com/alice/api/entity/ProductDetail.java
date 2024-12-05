package com.alice.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String productDetailId;
  private String name;
  private Long price;
  private Long stockQuantity;
  private Long soldQuantity;
  private Integer status;

  @ManyToOne
  private Product product;
  @ManyToOne
  private Color color;
  @ManyToOne
  private Size size;
}
