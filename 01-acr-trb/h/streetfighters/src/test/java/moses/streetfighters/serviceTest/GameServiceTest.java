package moses.streetfighters.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Game;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.repository.GameRepository;
import moses.streetfighters.service.GameService;

@SpringBootTest
@ActiveProfiles("test")
public class GameServiceTest {

  @Autowired
  GameService gameService;

  @MockBean
  GameRepository gameRepository;

  private final Long streetFighters3Id = 16L;
  private final String streetFighters3Title = "Street Fighters III";
  private final Integer streetFighters3Year = 1997;

  private final Long streetFighters6Id = 32L;
  private final String streetFighters6Title = "Street Fighters 6";
  private final Integer streetFighters6Year = 2023;

  @Test
  public void testGameCreation() {
    Game gameToCreate = new Game();
    gameToCreate.setTitle(streetFighters3Title);
    gameToCreate.setYear(streetFighters3Year);

    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when(gameRepository.save(any()))
      .thenReturn(gameToReturn);

    Game savedGame = gameService.create(gameToCreate);

    Mockito.verify(gameRepository).save(any());

    assertEquals(streetFighters3Id, savedGame.getId());
    assertEquals(streetFighters3Title, savedGame.getTitle());
    assertEquals(streetFighters3Year, savedGame.getYear());
  }

  @Test
  public void testFindGameById() throws GameNotFoundException {
    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when(gameRepository.findById(eq(streetFighters3Id)))
      .thenReturn(Optional.of(gameToReturn));
    
    Game returnedGame = gameService.findById(streetFighters3Id);

    Mockito.verify(gameRepository).findById(eq(streetFighters3Id));

    assertEquals(streetFighters3Id, returnedGame.getId());
    assertEquals(streetFighters3Title, returnedGame.getTitle());
    assertEquals(streetFighters3Year, returnedGame.getYear());
  }

  @Test
  public void testFindGameByIdNotFound() {
    Mockito.when(gameRepository.findById(streetFighters3Id))
      .thenReturn(Optional.empty());

    assertThrows(GameNotFoundException.class, 
      () -> gameService.findById(streetFighters3Id));

    Mockito.verify(gameRepository).findById(eq(streetFighters3Id));
  }

  @Test
  public void testGetAllGames() {
    Game gameA = new Game();
    gameA.setId(streetFighters3Id);
    gameA.setTitle(streetFighters3Title);
    gameA.setYear(streetFighters3Year);

    Game gameB = new Game();
    gameB.setId(streetFighters6Id);
    gameB.setTitle(streetFighters6Title);
    gameB.setYear(streetFighters6Year);

    List<Game> gamesToReturn = new ArrayList<Game>(Arrays.asList(gameA, gameB));

    Mockito.when(gameRepository.findAll())
      .thenReturn(gamesToReturn);

    List<Game> returnedGames = gameService.findAll();

    Mockito.verify(gameRepository).findAll();

    assertEquals(
      List.of(streetFighters3Id, streetFighters6Id),
      returnedGames.stream().map(Game::getId).toList()
    );
    assertEquals(
      List.of(streetFighters3Title, streetFighters6Title),
      returnedGames.stream().map(Game::getTitle).toList()
    );
    assertEquals(
      List.of(streetFighters3Year, streetFighters6Year),
      returnedGames.stream().map(Game::getYear).toList()
    );
  }

  @Test
  public void testUpdateGame() throws GameNotFoundException {
    Game gameToUpdate = new Game();
    gameToUpdate.setId(streetFighters3Id);
    gameToUpdate.setTitle(streetFighters3Title);
    gameToUpdate.setYear(streetFighters3Year);

    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters6Title);
    gameToReturn.setYear(streetFighters6Year);

    Mockito.when(gameRepository.findById(eq(streetFighters3Id)))
      .thenReturn(Optional.of(gameToReturn));

    Mockito.when(gameRepository.save(any()))
      .thenReturn(gameToUpdate);

    Game updatedGame = gameService.update(streetFighters3Id, gameToUpdate);

    Mockito.verify(gameRepository).findById(eq(streetFighters3Id));
    Mockito.verify(gameRepository).save(any());

    assertEquals(streetFighters3Id, updatedGame.getId());
    assertEquals(streetFighters3Title, updatedGame.getTitle());
    assertEquals(streetFighters3Year, updatedGame.getYear());
  }

  @Test
  public void testDeleteGameById() throws GameNotFoundException {
    Game gameToDelete = new Game();
    gameToDelete.setId(streetFighters3Id);
    gameToDelete.setTitle(streetFighters3Title);
    gameToDelete.setYear(streetFighters3Year);

    Mockito.when(gameRepository.findById(eq(streetFighters3Id)))
      .thenReturn(Optional.of(gameToDelete));

    Mockito.doNothing().when(gameRepository).deleteById(eq(streetFighters3Id));

    Game deletedGame = gameService.deleteById(streetFighters3Id);

    Mockito.verify(gameRepository).findById(eq(streetFighters3Id));
    Mockito.verify(gameRepository).deleteById(eq(streetFighters3Id));

    assertEquals(streetFighters3Id, deletedGame.getId());
    assertEquals(streetFighters3Title, deletedGame.getTitle());
    assertEquals(streetFighters3Year, deletedGame.getYear());
  }
}
