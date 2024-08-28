package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Game;

public record GameDto(Long id, String title, Integer year) {

  public static GameDto fromEntity(Game game) {
    return new GameDto(
      game.getId(),
      game.getTitle(),
      game.getYear()
    );
  }
}
