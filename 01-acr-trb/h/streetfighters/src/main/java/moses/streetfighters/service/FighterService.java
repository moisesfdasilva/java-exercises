package moses.streetfighters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.repository.FighterRepository;

@Service
public class FighterService {

  private final FighterRepository fighterRepository;

  @Autowired
  public FighterService(FighterRepository fighterRepository) {
    this.fighterRepository = fighterRepository;
  }

  public Fighter findById(Long id) throws FighterNotFoundException {
    return this.fighterRepository
      .findById(id)
      .orElseThrow(FighterNotFoundException::new);
  }

  public List<Fighter> findAll() {
    return fighterRepository.findAll();
  }

  public Fighter create(Fighter fighter) {
    return fighterRepository.save(fighter);
  }

  public Fighter update(Long id, Fighter fighter) throws FighterNotFoundException {
    Fighter fighterFromDb = findById(id);

    fighterFromDb.setName(fighter.getName());
    fighterFromDb.setGender(fighter.getGender());

    return fighterRepository.save(fighterFromDb);
  }

  public Fighter deleteById(Long id) throws FighterNotFoundException {
    Fighter fighter = findById(id);

    fighterRepository.deleteById(id);

    return fighter;
  }
}
