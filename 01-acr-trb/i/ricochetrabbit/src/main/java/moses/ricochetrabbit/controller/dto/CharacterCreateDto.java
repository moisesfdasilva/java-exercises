package moses.ricochetrabbit.controller.dto;

import java.time.LocalDate;

import moses.ricochetrabbit.entity.Character;

public record CharacterCreateDto(String name, String gender, String animal,
    LocalDate birthday) {

  public Character toEntity() {
    Character character = new Character();

    character.setName(name);
    character.setGender(gender);
    character.setAnimal(animal);
    character.setBirthday(birthday);

    return character;
  }
  
}
