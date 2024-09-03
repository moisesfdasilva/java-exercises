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
import moses.streetfighters.controller.dto.FighterDetailCreationDto;
import moses.streetfighters.controller.dto.FighterDetailDto;
import moses.streetfighters.controller.dto.FighterDto;
import moses.streetfighters.entity.Fighter;
import moses.streetfighters.exception.FighterDetailNotFoundException;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.exception.PublisherNotFoundException;
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

  @PostMapping("/{fighterId}/detail")
  @ResponseStatus(HttpStatus.CREATED)
  public FighterDetailDto createFighterDetail(@PathVariable Long fighterId,
      @RequestBody FighterDetailCreationDto fighterDetailCreationDto) throws FighterNotFoundException {
    return FighterDetailDto.fromEntity(
      fighterService.createFighterDetail(fighterId, fighterDetailCreationDto.toEntity())
    );
  }

  @GetMapping("/{fighterId}/detail")
  public FighterDetailDto getFighterDetail(@PathVariable Long fighterId)
      throws FighterNotFoundException, FighterDetailNotFoundException {
    return FighterDetailDto.fromEntity(
      fighterService.getFighterDetail(fighterId)
    );
  }

  @PutMapping("/{fighterId}/detail")
  public FighterDetailDto updateFighterDetail(@PathVariable Long fighterId,
      @RequestBody FighterDetailCreationDto fighterDetailCreationDto)
      throws FighterNotFoundException, FighterDetailNotFoundException {
    return FighterDetailDto.fromEntity(
      fighterService.updateFighterDetail(fighterId, fighterDetailCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{fighterId}/detail")
  public FighterDetailDto removFighterDetailDto(@PathVariable Long fighterId)
      throws FighterNotFoundException, FighterDetailNotFoundException {
    return FighterDetailDto.fromEntity(
      fighterService.removFighterDetail(fighterId)
    );
  }

  @PutMapping("/{fighterId}/publisher/{publisherId}")
  public FighterDto setFighterPublisher(@PathVariable Long fighhterId,
    @PathVariable Long publisherId) throws FighterNotFoundException, PublisherNotFoundException {
    return FighterDto.fromEntity(
      fighterService.setFighterPublisher(fighhterId, publisherId)
    );
  }

  @DeleteMapping("/{fighterId}/publisher")
  public FighterDto removeFighterPublisher(@PathVariable Long fighterId)
      throws FighterNotFoundException {
    return FighterDto.fromEntity(
      fighterService.removeFighterPublisher(fighterId)
    );
  }

  @PutMapping("/{fighterId}/fighter/{gameId}")
  public FighterDto addFighterGame(@PathVariable Long fightId, @PathVariable Long gameId)
      throws FighterNotFoundException, GameNotFoundException {
    return FighterDto.fromEntity(
      fighterService.addFighterGame(fightId, gameId)
    );
  }

  @DeleteMapping("/{fighterId}/fighter/{gameId}")
  public FighterDto removeFighterGame(@PathVariable Long fightId, @PathVariable Long gameId)
      throws FighterNotFoundException, GameNotFoundException {
    return FighterDto.fromEntity(
      fighterService.removeFighterGame(fightId, gameId)
    );
  }
}
