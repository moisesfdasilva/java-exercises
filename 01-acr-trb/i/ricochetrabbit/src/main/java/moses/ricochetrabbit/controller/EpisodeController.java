package moses.ricochetrabbit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moses.ricochetrabbit.entity.Episode;
import moses.ricochetrabbit.controller.dto.EpisodeDto;
import moses.ricochetrabbit.service.EpisodeService;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

  private final EpisodeService episodeService;

  @Autowired
  public EpisodeController(EpisodeService episodeService) {
    this.episodeService = episodeService;
  }

  @GetMapping("/lastEpisodes")
  public List<EpisodeDto> getEpisodesLastMonth() {
    List<Episode> episodes = episodeService.getEpisodesLastMonth();

    return episodes.stream().map(EpisodeDto::fromEntity).toList();
  }
}
