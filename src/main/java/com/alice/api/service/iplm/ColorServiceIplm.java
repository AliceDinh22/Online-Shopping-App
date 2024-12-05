package com.alice.api.service.iplm;

import com.alice.api.dto.ColorDTO;
import com.alice.api.entity.Color;
import com.alice.api.repository.ColorRepository;
import com.alice.api.service.ColorService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceIplm implements ColorService {
  //Controller -> Service -> Repository -> CRUD Database

  private final ColorRepository colorRepository;

  @Override
  public List<ColorDTO> getAll() {
    return mapToDto(colorRepository.findAll());
  }

  @Override
//  public List<ColorDTO> search(String key) {
//    return mapToDTO(colorRepository.getSearch(key));
//  }
  public List<ColorDTO> search(String key, Integer page, Integer size) {
    page = page == null ? 0 : page;
    size = size == null ? 2 : size;

    Page<Color> pageableResult = colorRepository.getSearch(key, PageRequest.of(page, size));

    return mapToDto(pageableResult.getContent());
  }

  @Override
  public ColorDTO getById(Long id) {
    Color color = colorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new ColorDTO(color.getId(), color.getName());
  }

  @Override
  public void create(ColorDTO colorDTO) {
    Color color = new Color();
    color.setName(colorDTO.getName());
    colorRepository.save(color);
  }

  @Override
  public void update(ColorDTO colorDTO, Long id) {
    if (!colorRepository.existsById(id)) {
      throw new EntityNotFoundException("Id not found!");
    }

    colorRepository.save(new Color(id, colorDTO.getName()));
  }

  @Override
  public void delete(Long id) {
    if (!colorRepository.existsById(id)) {
      throw new EntityNotFoundException("Id not found!");
    }

    colorRepository.deleteById(id);
  }

  private List<ColorDTO> mapToDto(List<Color> listEntity) {
    List<ColorDTO> list = new ArrayList<>();

    if (listEntity == null) {
      return list;
    }

    for (Color entity : listEntity) {
      list.add(new ColorDTO(entity.getId(), entity.getName()));
    }

    return list;
  }
}
