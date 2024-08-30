package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.FighterDetail;

public record FighterDetailDto(Long id, String origin) {

  public static FighterDetailDto fromEntity(FighterDetail fighterDetail) {
    return new FighterDetailDto(
      fighterDetail.getId(),
      fighterDetail.getOrigin()
    );
  }
}
