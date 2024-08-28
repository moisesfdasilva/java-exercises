package moses.streetfighters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moses.streetfighters.entity.Game;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.repository.GameRepository;

@Service
public class GameService {

  private final GameRepository gameRepository;

  @Autowired
  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public Game findById(Long id) throws GameNotFoundException {
    return this.gameRepository
      .findById(id)
      .orElseThrow(GameNotFoundException::new);
  }

  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  public Game create(Game game) {
    return gameRepository.save(game);
  }

  public Game update(Long id, Game game) throws GameNotFoundException {
    Game gameFromDb = findById(id);

    gameFromDb.setTitle(game.getTitle());
    gameFromDb.setYear(game.getYear());

    return gameRepository.save(gameFromDb);
  }

  public Game deleteById(Long id) throws GameNotFoundException {
    Game game = findById(id);

    gameRepository.deleteById(id);

    return game;
  }
}
