package moses.streetfighters.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.controller.GameController;
import moses.streetfighters.controller.dto.GameCreationDto;
import moses.streetfighters.controller.dto.GameDto;
import moses.streetfighters.entity.Game;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.service.GameService;

@SpringBootTest
@ActiveProfiles("test")
public class GameControllerTest {

  @Autowired
  GameController gameController;

  @MockBean
  GameService gameService;

  private final Long streetFighters3Id = 16L;
  private final String streetFighters3Title = "Street Fighters III";
  private final Integer streetFighters3Year = 1997;

  private final Long streetFighters6Id = 32L;
  private final String streetFighters6Title = "Street Fighters 6";
  private final Integer streetFighters6Year = 2023;

  @Test
  public void testCreateGame() {
    GameCreationDto gameCreationDto = new GameCreationDto(
      streetFighters3Title, streetFighters3Year);

    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when((gameService.create(any())))
      .thenReturn(gameToReturn);

    GameDto savedGame = gameController.createGame(gameCreationDto);

    Mockito.verify(gameService).create(any());

    assertEquals(streetFighters3Id, savedGame.id());
    assertEquals(streetFighters3Title, savedGame.title());
    assertEquals(streetFighters3Year, savedGame.year());
  }

  @Test
  public void testGetGameById() throws GameNotFoundException {
    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when((gameService.findById(eq(streetFighters3Id))))
      .thenReturn(gameToReturn);

    GameDto returnedGame = gameController.getGameById(streetFighters3Id);

    Mockito.verify(gameService).findById(eq(streetFighters3Id));

    assertEquals(streetFighters3Id, returnedGame.id());
    assertEquals(streetFighters3Title, returnedGame.title());
    assertEquals(streetFighters3Year, returnedGame.year());
  }

  @Test
  public void testGetGameByIdNotFound() throws GameNotFoundException {
    Mockito.when(gameService.findById(any()))
      .thenThrow(new GameNotFoundException());

    assertThrows(GameNotFoundException.class,
      () -> gameController.getGameById(any()));

    Mockito.verify(gameService).findById(any());
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

    List<Game> games = List.of(gameA, gameB);

    Mockito.when(gameService.findAll()).thenReturn(games);

    List<GameDto> returnedGames = gameController.getAllGames();

    Mockito.verify(gameService).findAll();

    assertEquals(
      List.of(streetFighters3Id, streetFighters6Id),
      returnedGames.stream().map(GameDto::id).toList()
    );
    assertEquals(
      List.of(streetFighters3Title, streetFighters6Title),
      returnedGames.stream().map(GameDto::title).toList()
    );
    assertEquals(
      List.of(streetFighters3Year, streetFighters6Year),
      returnedGames.stream().map(GameDto::year).toList()
    );
  }

  @Test
  public void testUpdateGame() throws GameNotFoundException {
    GameCreationDto gameToUpdate = new GameCreationDto(
      streetFighters3Title, streetFighters3Year);

    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when(gameService.update(eq(streetFighters3Id), any()))
      .thenReturn(gameToReturn);

    GameDto updatedGame = gameController.updateGame(
      streetFighters3Id, gameToUpdate);

    Mockito.verify(gameService).update(eq(streetFighters3Id), any());

    assertEquals(streetFighters3Id, updatedGame.id());
    assertEquals(streetFighters3Title, updatedGame.title());
    assertEquals(streetFighters3Year, updatedGame.year());
  }

  @Test
  public void testDeleteGame() throws GameNotFoundException {
    Game gameToReturn = new Game();
    gameToReturn.setId(streetFighters3Id);
    gameToReturn.setTitle(streetFighters3Title);
    gameToReturn.setYear(streetFighters3Year);

    Mockito.when(gameService.deleteById(eq(streetFighters3Id)))
      .thenReturn(gameToReturn);

    GameDto deletedGame = gameController.deleteGameById(streetFighters3Id);

    Mockito.verify(gameService).deleteById(eq(streetFighters3Id));

    assertEquals(streetFighters3Id, deletedGame.id());
    assertEquals(streetFighters3Title, deletedGame.title());
    assertEquals(streetFighters3Year, deletedGame.year());
  }
  
}
