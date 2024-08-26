package moses.podcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import moses.podcast.model.PodCastDto;
import moses.podcast.model.Podcast;
import moses.podcast.model.PodcastCreationDto;
import moses.podcast.service.PodcastService;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {

  private PodcastService service;

  @Autowired
  public PodcastController(PodcastService service) {
    this.service = service;
  }

  @GetMapping
  public String getRoot() {
    return "Yay podcasts!";
  }

  @GetMapping("/{id}")
  public ResponseEntity<PodCastDto> getPodcast(@PathVariable Long id) {
    if (id > 1000) {
      return ResponseEntity.notFound().build();
    }

    Podcast podcast = service.findPodcastById(id);
    PodCastDto podCastDto = new PodCastDto(
      podcast.getId(), podcast.getName(), podcast.getUrl());

    return ResponseEntity.ok(podCastDto);
  }

  // .../search?title=computer
  @GetMapping("/search")
  public String searchPodcast(@RequestParam String title) {
    return "Uou! Podcast with title: %s!!".formatted(title);
  }

  @PostMapping
  public ResponseEntity<PodCastDto> newPodcast(@RequestBody PodcastCreationDto podcastCreationDto) {
    Podcast newPodcast = new Podcast();
    newPodcast.setName(podcastCreationDto.name());
    newPodcast.setUrl(podcastCreationDto.url());

    Podcast savedPodcast = service.savePodcast(newPodcast);

    PodCastDto podCastDto = new PodCastDto(
      savedPodcast.getId(),
      savedPodcast.getName(),
      savedPodcast.getUrl()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(podCastDto);
  }
}
