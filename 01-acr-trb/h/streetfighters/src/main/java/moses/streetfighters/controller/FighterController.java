package moses.streetfighters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moses.streetfighters.controller.dto.FighterCreationDto;
import moses.streetfighters.controller.dto.FighterDto;
import moses.streetfighters.entity.Fighter;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.service.FighterService;

@RestController
@RequestMapping(value = "/fighters")
public class FighterController {
  
  private final FighterService fighterService;

  @Autowired
  public FighterController(FighterService fighterService) {
    this.fighterService = fighterService;
  }

  @GetMapping("/{id}")
  public FighterDto getFighterById(@PathVariable Long id) throws FighterNotFoundException {
    return FighterDto.fromEntity(
      fighterService.findById(id)
    );
  }

  @GetMapping
  public List<FighterDto> getAllFighters() {
    List<Fighter> allFighters = fighterService.findAll();
    return allFighters.stream()
      .map(FighterDto::fromEntity)
      .toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FighterDto createFighter(@RequestBody FighterCreationDto fighterCreationDto) {
    return FighterDto.fromEntity(
      fighterService.create(fighterCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public FighterDto updateFighter(@PathVariable Long id, 
    @RequestBody FighterCreationDto fighterCreationDto) throws FighterNotFoundException {
    return FighterDto.fromEntity(
      fighterService.update(id, fighterCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public FighterDto deleteFighterById(@PathVariable Long id) throws FighterNotFoundException {
    return FighterDto.fromEntity(
      fighterService.deleteById(id)
    );
  }
}
