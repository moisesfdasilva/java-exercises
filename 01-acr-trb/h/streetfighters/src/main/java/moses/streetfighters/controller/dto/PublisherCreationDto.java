package moses.streetfighters.controller.dto;

import moses.streetfighters.entity.Publisher;

public record PublisherCreationDto(String name, String country) {
  
  public Publisher toEntity() {
    return new Publisher(name, country);
  }
}
