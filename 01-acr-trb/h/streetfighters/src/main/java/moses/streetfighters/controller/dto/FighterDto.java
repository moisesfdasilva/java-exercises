package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Fighter;

public record FighterDto(Long id, String name, String gender) {
  
  public static FighterDto fromEntity(Fighter fighter) {
    return new FighterDto(
      fighter.getId(),
      fighter.getName(),
      fighter.getGender()
    );
  }
}
