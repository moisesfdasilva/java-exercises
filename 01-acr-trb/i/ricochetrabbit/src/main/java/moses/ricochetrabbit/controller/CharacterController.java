package moses.ricochetrabbit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moses.ricochetrabbit.entity.Character;
import moses.ricochetrabbit.entity.Episode;
import moses.ricochetrabbit.controller.dto.CharacterCreateDto;
import moses.ricochetrabbit.controller.dto.CharacterDto;
import moses.ricochetrabbit.controller.dto.EpisodeCreateDto;
import moses.ricochetrabbit.controller.dto.EpisodeDto;
import moses.ricochetrabbit.service.CharacterService;
import moses.ricochetrabbit.service.EpisodeService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

  private final CharacterService characterService;
  private final EpisodeService episodeService;

  @Autowired
  public CharacterController(CharacterService characterService,
      EpisodeService episodeService) {
    this.characterService = characterService;
    this.episodeService = episodeService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CharacterDto createCharacter(@RequestBody CharacterCreateDto characterCreate) {
    Character newCharacter = characterService.createCharacter(
      characterCreate.toEntity());

    return CharacterDto.fromEntity(newCharacter);
  }

  @GetMapping("/{characterId}")
  public CharacterDto getCharacterById(@PathVariable Long characterId) {
    Character character = characterService.getCharacterById(characterId);

    return CharacterDto.fromEntity(character);
  }

  @GetMapping
  public List<CharacterDto> getAllCharacters() {
    List<Character> characters = characterService.getAllCharacters();

    return characters.stream().map(CharacterDto::fromEntity).toList();
  }

  @PostMapping("/{characterId}/episode")
  @ResponseStatus(HttpStatus.CREATED)
  public EpisodeDto createEpisode(@RequestBody EpisodeCreateDto episodeCreate,
      @PathVariable Long characterId) {
    Episode episode = episodeService.createEpisode(episodeCreate.toEntity(), characterId);

    return EpisodeDto.fromEntity(episode);
  }
}
