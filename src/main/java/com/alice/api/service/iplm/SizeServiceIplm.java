package com.alice.api.service.iplm;

import com.alice.api.dto.SizeDTO;
import com.alice.api.entity.Size;
import com.alice.api.repository.SizeRepository;
import com.alice.api.service.SizeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceIplm implements SizeService {

  private final SizeRepository sizeRepository;

  @Override
  public List<SizeDTO> getAll() {
    return mapToDto(sizeRepository.findAll());
  }

  @Override
  public SizeDTO getById(Long id) {
    Size size = sizeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found!"));

    return new SizeDTO(size.getId(), size.getName());
  }

  @Override
  public void create(SizeDTO sizeDTO) {
    Size size = new Size();
    size.setName(sizeDTO.getName());
    sizeRepository.save(size);
  }

  @Override
  public void update(SizeDTO sizeDTO, Long id) {
    if (!sizeRepository.existsById(id)) {
      throw new EntityNotFoundException("Id not found!");
    }

    sizeRepository.save(new Size(id, sizeDTO.getName()));
  }

  @Override
  public void delete(Long id) {
    if (!sizeRepository.existsById(id)) {
      throw new EntityNotFoundException("Id not found!");
    }

    sizeRepository.deleteById(id);
  }

  private List<SizeDTO> mapToDto(List<Size> listEntity) {
    List<SizeDTO> list = new ArrayList<>();

    if (listEntity == null) {
      return list;
    }

    for (Size entity : listEntity) {
      list.add(new SizeDTO(entity.getId(), entity.getName()));
    }

    return list;
  }
}
