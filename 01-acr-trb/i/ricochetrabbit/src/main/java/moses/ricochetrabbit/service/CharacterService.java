package moses.ricochetrabbit.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.ricochetrabbit.entity.Character;
import moses.ricochetrabbit.repository.CharacterRepository;

@Service
public class CharacterService {

  private final CharacterRepository characterRepository;

  @Autowired
  public CharacterService(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  public Character createCharacter(Character character) {
    LocalDateTime currentDate = LocalDateTime.now();

    character.setCreatedDate(currentDate);

    return characterRepository.save(character);
  }

  public Character getCharacterById(Long characteId) {
    return characterRepository.findById(characteId).orElseThrow();
  }

  public List<Character> getAllCharacters() {
    return characterRepository.findAll();
  }

}
