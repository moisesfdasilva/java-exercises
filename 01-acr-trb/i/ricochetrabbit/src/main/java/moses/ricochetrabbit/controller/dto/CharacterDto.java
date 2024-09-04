package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import moses.ricochetrabbit.entity.Character;

public record CharacterDto(Long id, String name, String gender,
    String animal, LocalDate birthday, LocalDateTime createdDate,
    List<EpisodeDto> episodes) {

  public static CharacterDto fromEntity(Character character) {
    List<EpisodeDto> episodesDto = character.getEpisodes() != null ?
      character.getEpisodes().stream().map(EpisodeDto::fromEntity).toList() : null;

    return new CharacterDto(
      character.getId(),
      character.getName(), 
      character.getGender(), 
      character.getAnimal(), 
      character.getBirthday(), 
      character.getCreatedDate(),
      episodesDto
    );
  }

}
