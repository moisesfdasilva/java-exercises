package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Game;

public record GameCreationDto(String title, Integer year) {
  
  public Game toEntity() {
    return new Game(title, year);
  }
}
