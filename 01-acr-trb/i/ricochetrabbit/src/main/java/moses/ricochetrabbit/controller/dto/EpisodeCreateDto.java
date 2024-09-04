package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;

import moses.ricochetrabbit.entity.Episode;

public record EpisodeCreateDto(String title, String description, 
    LocalDate savedDate) {

  public Episode toEntity() {
    Episode episode = new Episode();
    episode.setTitle(title);
    episode.setDescription(description);
    episode.setSavedDate(savedDate);

    return episode;
  }
}
