package moses.streetfighters.controller.dto;

import java.util.List;

import moses.streetfighters.entity.Fighter;

public record FighterDto(
  Long id,
  String name,
  String gender,
  PublisherDto publisherDto,
  List<GameDto> games) {

  public static FighterDto fromEntity(Fighter fighter) {
    PublisherDto publisherDto = fighter.getPublisher() != null ?
      PublisherDto.fromEntity(fighter.getPublisher()) : null;

    return new FighterDto(
      fighter.getId(),
      fighter.getName(),
      fighter.getGender(),
      publisherDto,
      fighter.getGames().stream()
        .map(GameDto::fromEntity)
        .toList()
    );
  }
}
