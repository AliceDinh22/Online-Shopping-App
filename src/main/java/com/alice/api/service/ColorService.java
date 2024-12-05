package com.alice.api.service;

import com.alice.api.dto.ColorDTO;
import java.util.List;

public interface ColorService {

  List<ColorDTO> getAll();

  //  List<ColorDTO> search(String key);
  List<ColorDTO> search(String key, Integer page, Integer size);

  ColorDTO getById(Long id);

  void create(ColorDTO colorDTO);

  void update(ColorDTO colorDTO, Long id);

  void delete(Long id);
}
