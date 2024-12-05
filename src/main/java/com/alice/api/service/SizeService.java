package com.alice.api.service;

import com.alice.api.dto.SizeDTO;
import java.util.List;

public interface SizeService {

  List<SizeDTO> getAll();

  SizeDTO getById(Long id);

  void create(SizeDTO sizeDTO);

  void update(SizeDTO sizeDTO, Long id);

  void delete(Long id);

}
