package moses.ricochetrabbit.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.ricochetrabbit.entity.Character;
import moses.ricochetrabbit.entity.Episode;
import moses.ricochetrabbit.repository.EpisodeRepository;

@Service
public class EpisodeService {

  private final EpisodeRepository episodeRepository;
  private final CharacterService characterService;

  @Autowired
  public EpisodeService(EpisodeRepository episodeRepository,
      CharacterService characterService) {
    this.episodeRepository = episodeRepository;
    this.characterService = characterService;
  }

  public Episode createEpisode(Episode episode, long characterId) {
    Character character = characterService.getCharacterById(characterId);
    LocalDate currentDate = LocalDate.now();

    episode.setCharacter(character);
    episode.setSavedDate(currentDate);

    return episodeRepository.save(episode);
  }

  public List<Episode> getAllEpisodes() {
    return episodeRepository.findAll();
  }

  public List<Episode> getEpisodesLastMonth() {
    List<Episode> episodes = getAllEpisodes();

    return episodes.stream()
      .filter(episode -> episode.getSavedDate()
        .isAfter(LocalDate.now().minusDays(30))
      ).toList();
  }
}
