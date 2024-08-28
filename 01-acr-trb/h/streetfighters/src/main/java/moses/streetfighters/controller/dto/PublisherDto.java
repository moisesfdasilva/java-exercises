package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Publisher;

public record PublisherDto(Long id, String name, String country) {

  public static PublisherDto fromEntity(Publisher publisher) {
    return new PublisherDto(
      publisher.getId(),
      publisher.getName(),
      publisher.getCountry()
    );
  }
}
