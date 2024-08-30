package moses.streetfighters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.entity.Publisher;
import moses.streetfighters.entity.FighterDetail;
import moses.streetfighters.entity.Game;
import moses.streetfighters.exception.FighterDetailNotFoundException;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.repository.FighterDetailRepository;
import moses.streetfighters.repository.FighterRepository;

@Service
public class FighterService {

  private final FighterRepository fighterRepository;
  private final FighterDetailRepository fighterDetailRepository;
  private final PublisherService publisherService;
  private final GameService gameService;

  @Autowired
  public FighterService(FighterRepository fighterRepository,
      FighterDetailRepository fighterDetailRepository,
      PublisherService publisherService, GameService gameService) {
    this.fighterRepository = fighterRepository;
    this.fighterDetailRepository = fighterDetailRepository;
    this.publisherService = publisherService;
    this.gameService = gameService;
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

  public FighterDetail createFighterDetail(Long fighterId, FighterDetail fighterDetail) 
      throws FighterNotFoundException {
    Fighter fighter = findById(fighterId);

    fighterDetail.setFighter(fighter);
    return fighterDetailRepository.save(fighterDetail);
  }

  public FighterDetail getFighterDetail(Long fighterId)
      throws FighterNotFoundException, FighterDetailNotFoundException {
    Fighter fighter = findById(fighterId);

    FighterDetail fighterDetailFromDb = fighter.getDetails();

    if(fighterDetailFromDb == null) {
      throw new FighterDetailNotFoundException();
    }

    return fighterDetailFromDb;
  }

  public FighterDetail updateFighterDetail(Long FighterId, FighterDetail fighterDetail) 
      throws FighterNotFoundException, FighterDetailNotFoundException {
    FighterDetail fighterDetailFromDb = getFighterDetail(FighterId);

    fighterDetailFromDb.setOrigin(fighterDetail.getOrigin());

    return fighterDetailRepository.save(fighterDetailFromDb);
  }

  public FighterDetail removFighterDetail(Long fighterId)
      throws FighterNotFoundException, FighterDetailNotFoundException {
    Fighter fighter = findById(fighterId);
    FighterDetail fighterDetail = fighter.getDetails();

    if (fighterDetail == null) {
      throw new FighterDetailNotFoundException();
    }

    fighter.setDetails(null);
    fighterDetail.setFighter(null);

    fighterDetailRepository.delete(fighterDetail);

    return fighterDetail;
  }

  public Fighter setFighterPublisher(Long fighterId, Long publisherId)
      throws FighterNotFoundException, PublisherNotFoundException {
    Fighter fighter = findById(fighterId);

    Publisher publisher = publisherService.findById(publisherId);

    fighter.setPublisher(publisher);

    return fighterRepository.save(fighter);
  }

  public Fighter removeFighterPublisher(Long fighterId) throws FighterNotFoundException {
    Fighter fighter = findById(fighterId);

    fighter.setPublisher(null);

    return fighterRepository.save(fighter);
  }

  public Fighter addFighterGame(Long fighterId, Long gameId)
      throws FighterNotFoundException, GameNotFoundException {
    Fighter fighter = findById(fighterId);
    Game game = gameService.findById(gameId);

    fighter.getGames().add(game);

    return fighterRepository.save(fighter);
  }

  public Fighter removeFighterGame(Long fighterId, Long gameId)
      throws FighterNotFoundException, GameNotFoundException {
    Fighter fighter = findById(fighterId);
    Game game = gameService.findById(gameId);

    fighter.getGames().remove(game);

    return fighterRepository.save(fighter);
  }
}
