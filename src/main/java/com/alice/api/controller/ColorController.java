package com.alice.api.controller;

import com.alice.api.dto.ColorDTO;
import com.alice.api.dto.ResponseDTO;
import com.alice.api.service.ColorService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {

  //Controller -> Service -> Repository -> CRUD Database
  private final ColorService colorService;

  @GetMapping
//  public List<ColorDTO> getAll() { return colorService.getAll(); }
  public ResponseDTO<List<ColorDTO>> getAll() {
    return ResponseDTO.<List<ColorDTO>>builder()
        .data(colorService.getAll())
        .status(200)
        .message("Get all colors successfully!")
        .build();
  }

//  @GetMapping("/search")
//  public ResponseDTO<List<ColorDTO>> search(
//      @RequestParam(value = "key", required = false) String key) {
//    return ResponseDTO.<List<ColorDTO>>builder()
//        .data(colorService.search(key))
//        .status(200)
//        .message("Search successfully!")
//        .build();
//  }

  @GetMapping("/search")
  public ResponseDTO<List<ColorDTO>> search(
      @RequestParam(value = "key", required = false) String key,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size) {
    List<ColorDTO> result = colorService.search(key, page, size);

    if (result.isEmpty()) {
      return ResponseDTO.<List<ColorDTO>>builder()
          .data(result)
          .status(204)
          .message("No colors found!")
          .build();
    } else {
      return ResponseDTO.<List<ColorDTO>>builder()
          .data(result)
          .status(200)
          .message("Search successfully!")
          .build();
    }
  }

  @GetMapping("/{id}")
//  public ColorDTO getById(@PathVariable int id) { return colorService.getById(id); }
  public ResponseDTO<ColorDTO> getById(@PathVariable Long id) {
    return ResponseDTO.<ColorDTO>builder()
        .data(colorService.getById(id))
        .status(200)
        .message("Get color by id successfully!")
        .build();
  }


  @PostMapping
//  public void create(@RequestBody @Valid ColorDTO colorDTO) { colorService.create(colorDTO); }
  public ResponseDTO<Void> create(@RequestBody @Valid ColorDTO colorDTO) {
    colorService.create(colorDTO);
    return ResponseDTO.<Void>builder()
        .status(201)
        .message("Create color successfully!")
        .build();
  }

  @PutMapping("/{id}")
//  public void update(@RequestBody @Valid ColorDTO colorDTO, @PathVariable int id) { colorService.update(colorDTO, id); }
  public ResponseDTO<Void> update(
      @RequestBody @Valid ColorDTO colorDTO,
      @PathVariable Long id) {
    colorService.update(colorDTO, id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Update color by id successfully!")
        .build();
  }


  @DeleteMapping("/{id}")
//  public void delete(@PathVariable int id) { colorService.delete(id); }
  public ResponseDTO<Void> delete(@PathVariable Long id) {
    colorService.delete(id);
    return ResponseDTO.<Void>builder()
        .status(200)
        .message("Delete color by id successfully!")
        .build();
  }
}
