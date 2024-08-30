package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.FighterDetail;

public record FighterDetailCreationDto(String origin) {

  public FighterDetail toEntity() {
    return new FighterDetail(origin);
  }
}
