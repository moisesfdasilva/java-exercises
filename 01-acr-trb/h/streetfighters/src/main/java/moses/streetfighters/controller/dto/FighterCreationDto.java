package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Fighter;

public record FighterCreationDto(String name, String gender) {
  
  public Fighter toEntity() {
    return new Fighter(name, gender);
  }
}
