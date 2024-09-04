package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;

import moses.ricochetrabbit.entity.Episode;

public record EpisodeDto(Long id, String title, String description, 
    LocalDate savedDate) {

  public static EpisodeDto fromEntity(Episode episode) {
    return new EpisodeDto(
      episode.getId(), 
      episode.getTitle(), 
      episode.getDescription(), 
      episode.getSavedDate()
    );
  }

}
