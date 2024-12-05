package com.alice.api.controller;

import com.alice.api.dto.ResponseDTO;
import com.alice.api.dto.SizeDTO;
import com.alice.api.service.SizeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sizes")
public class SizeController {

  private final SizeService sizeService;

  @GetMapping
  public ResponseDTO<List<SizeDTO>> getAll() {
    return ResponseDTO.<List<SizeDTO>>builder()
        .data(sizeService.getAll())
        .status(200)
        .message("Get all size successfully!")
        .build();
  }

  @GetMapping("/{id}")
  public ResponseDTO<SizeDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<SizeDTO>builder()
        .data(sizeService.getById(id))
        .status(200)
        .message("Get size by id successfully!")
        .build();
  }


  @PostMapping
  public ResponseDTO<Void> create(@RequestBody @Valid SizeDTO SizeDTO) {
    sizeService.create(SizeDTO);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create size successfully!")
        .build();
  }

  @PutMapping("/{id}")
  public ResponseDTO<Void> update(
      @RequestBody @Valid SizeDTO SizeDTO,
      @PathVariable Long id) {
    sizeService.update(SizeDTO, id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Update size by id successfully!")
        .build();
  }


  @DeleteMapping("/{id}")
  public ResponseDTO<Void> delete(@PathVariable Long id) {
    sizeService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete size by id successfully!")
        .build();
  }
}
