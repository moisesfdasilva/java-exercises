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

import moses.streetfighters.controller.dto.GameCreationDto;
import moses.streetfighters.controller.dto.GameDto;
import moses.streetfighters.entity.Game;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.service.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
  
  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/{id}")
  public GameDto getGameById(@PathVariable Long id) throws GameNotFoundException {
    return GameDto.fromEntity(
      gameService.findById(id)
    );
  }

  @GetMapping
  public List<GameDto> getAllGames() {
    List<Game> allGames = gameService.findAll();
    return allGames.stream()
      .map(GameDto::fromEntity)
      .toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameDto createGame(@RequestBody GameCreationDto gameCreationDto) {
    return GameDto.fromEntity(
      gameService.create(gameCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public GameDto updateGame(@PathVariable Long id, 
    @RequestBody GameCreationDto gameCreationDto) throws GameNotFoundException {
    return GameDto.fromEntity(
      gameService.update(id, gameCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public GameDto deleteGameById(@PathVariable Long id) throws GameNotFoundException {
    return GameDto.fromEntity(
      gameService.deleteById(id)
    );
  }
}
