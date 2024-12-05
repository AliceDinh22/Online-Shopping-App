package com.alice.api.repository;

import com.alice.api.entity.Color;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColorRepository extends JpaRepository<Color, Long> {

  //@Query(value = "SELECT * FROM color WHERE name LIKE CONCAT('%',:key,'%')", nativeQuery = true)
  @Query("SELECT c FROM Color c WHERE :key IS NULL OR c.name LIKE CONCAT('%',:key,'%')")
//  List<Color> getSearch(@Param("key") String key);
  Page<Color> getSearch(@Param("key") String key, Pageable pageable);

}
