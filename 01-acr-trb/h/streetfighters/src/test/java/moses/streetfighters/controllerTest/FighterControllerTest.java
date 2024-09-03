package moses.streetfighters.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.controller.FighterController;
import moses.streetfighters.controller.dto.FighterCreationDto;
import moses.streetfighters.controller.dto.FighterDetailCreationDto;
import moses.streetfighters.controller.dto.FighterDetailDto;
import moses.streetfighters.controller.dto.FighterDto;
import moses.streetfighters.controller.dto.GameDto;
import moses.streetfighters.entity.Fighter;
import moses.streetfighters.entity.FighterDetail;
import moses.streetfighters.entity.Game;
import moses.streetfighters.entity.Publisher;
import moses.streetfighters.exception.FighterDetailNotFoundException;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.service.FighterService;

@SpringBootTest
@ActiveProfiles("test")
public class FighterControllerTest {

  @Autowired
  FighterController fighterController;

  @MockBean
  FighterService fighterService;

  private final Long akumaId = 556L;
  private final String akumaName = "Akuma";
  private final String akumaGender = "male";
  private final String akumaOrigin = "Japan";

  private final Long cammyId = 16L;
  private final String cammyName = "Cammy";
  private final String cammyGender = "female";

  private final Long detailId = 455L;

  private final Long capcomId = 15L;
  private final String capcomName = "Capcom";
  private final String capcomCountry = "Japan";

  private final Byte valueNull = null;

  private final Long streetFighters3Id = 16L;
  private final String streetFighters3Title = "Street Fighters III";
  private final Integer streetFighters3Year = 1997;

  @Test
  public void testCreateFighter() {
    FighterCreationDto fighterCreationDto = new FighterCreationDto(
      akumaName, akumaGender);

    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.create(any()))
      .thenReturn(fighterToReturn);

    FighterDto savedFighter = fighterController.createFighter(fighterCreationDto);

    Mockito.verify(fighterService).create(any());

    assertEquals(akumaId, savedFighter.id());
    assertEquals(akumaName, savedFighter.name());
    assertEquals(akumaGender, savedFighter.gender());
  }

  @Test
  public void testGetFighterById() throws FighterNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.findById(eq(akumaId)))
      .thenReturn(fighterToReturn);

    FighterDto returnedFighter = fighterController.getFighterById(akumaId);

    Mockito.verify(fighterService).findById(eq(akumaId));

    assertEquals(akumaId, returnedFighter.id());
    assertEquals(akumaName, returnedFighter.name());
    assertEquals(akumaGender, returnedFighter.gender());
  }

  @Test
  public void testGetFighterByIdNotFound() throws FighterNotFoundException {
    Mockito.when(fighterService.findById(any()))
      .thenThrow(new FighterNotFoundException());

    assertThrows(FighterNotFoundException.class,
      () -> fighterController.getFighterById(any()));

    Mockito.verify(fighterService).findById(any());
  }

  @Test
  public void testGetAllFighters() {
    Fighter fighterA = new Fighter();
    fighterA.setId(akumaId);
    fighterA.setName(akumaName);
    fighterA.setGender(akumaGender);

    Fighter fighterB = new Fighter();
    fighterB.setId(cammyId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    List<Fighter> fighters = List.of(fighterA, fighterB);

    Mockito.when(fighterService.findAll()).thenReturn(fighters);

    List<FighterDto> returnedFighters = fighterController.getAllFighters();

    Mockito.verify(fighterService).findAll();

    assertEquals(
      List.of(akumaId, cammyId),
      returnedFighters.stream().map(FighterDto::id).toList()
    );
    assertEquals(
      List.of(akumaName, cammyName),
      returnedFighters.stream().map(FighterDto::name).toList()
    );
    assertEquals(
      List.of(akumaGender, cammyGender),
      returnedFighters.stream().map(FighterDto::gender).toList()
    );
  }

  @Test
  public void testUpdateFighter() throws FighterNotFoundException {
    FighterCreationDto fighterToUpdate = new FighterCreationDto(
      akumaName, akumaGender);

    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.update(eq(akumaId), any()))
      .thenReturn(fighterToReturn);

    FighterDto updatedFighter = fighterController
      .updateFighter(akumaId, fighterToUpdate);

    Mockito.verify(fighterService).update(eq(akumaId), any());

    assertEquals(akumaId, updatedFighter.id());
    assertEquals(akumaName, updatedFighter.name());
    assertEquals(akumaGender, updatedFighter.gender());
  }

  @Test
  public void testDeleteFighter() throws FighterNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.deleteById(eq(akumaId)))
      .thenReturn(fighterToReturn);

    FighterDto deletedFighter = fighterController.deleteFighterById(akumaId);

    Mockito.verify(fighterService).deleteById(eq(akumaId));

    assertEquals(akumaId, deletedFighter.id());
    assertEquals(akumaName, deletedFighter.name());
    assertEquals(akumaGender, deletedFighter.gender());
  }

  @Test
  public void testCreateFighterDetail() throws FighterNotFoundException {
    FighterDetailCreationDto fighterDetailCreationDto = new FighterDetailCreationDto(
      akumaOrigin);

    FighterDetail fighterDetailToReturn = new FighterDetail();
    fighterDetailToReturn.setId(detailId);
    fighterDetailToReturn.setOrigin(akumaOrigin);

    Mockito.when(fighterService.createFighterDetail(eq(akumaId), any()))
      .thenReturn(fighterDetailToReturn);

    FighterDetailDto createdFighterDetail = fighterController
      .createFighterDetail(akumaId, fighterDetailCreationDto);

    Mockito.verify(fighterService).createFighterDetail(eq(akumaId), any());

    assertEquals(detailId, createdFighterDetail.id());
    assertEquals(akumaOrigin, createdFighterDetail.origin());
  }

  @Test
  public void testGetFighterDetail() throws FighterNotFoundException, 
      FighterDetailNotFoundException {
    FighterDetail fighterDetailToReturn = new FighterDetail();
    fighterDetailToReturn.setId(detailId);
    fighterDetailToReturn.setOrigin(akumaOrigin);

    Mockito.when(fighterService.getFighterDetail(eq(akumaId)))
      .thenReturn(fighterDetailToReturn);

    FighterDetailDto returnedFighterDetail = fighterController
      .getFighterDetail(akumaId);

    Mockito.verify(fighterService).getFighterDetail(eq(akumaId));

    assertEquals(detailId, returnedFighterDetail.id());
    assertEquals(akumaOrigin, returnedFighterDetail.origin());
  }

  @Test
  public void testGetFighterDetailNotFoundFighter() throws FighterNotFoundException, 
      FighterDetailNotFoundException {
    Mockito.when(fighterService.getFighterDetail(any()))
      .thenThrow(new FighterNotFoundException());

    assertThrows(FighterNotFoundException.class,
      () -> fighterController.getFighterDetail(any()));

    Mockito.verify(fighterService).getFighterDetail(any());
  }

  @Test
  public void testGetFighterDetailNotFoundDetail() throws FighterNotFoundException, 
      FighterDetailNotFoundException {
    Mockito.when(fighterService.getFighterDetail(any()))
      .thenThrow(new FighterDetailNotFoundException());

    assertThrows(FighterDetailNotFoundException.class,
      () -> fighterController.getFighterDetail(any()));

    Mockito.verify(fighterService).getFighterDetail(any());
  }

  @Test
  public void testUpdateFighterDetail() throws FighterNotFoundException, 
      FighterDetailNotFoundException {
    FighterDetailCreationDto fighterDetailCreationDto = new FighterDetailCreationDto(
      akumaOrigin);

    FighterDetail fighterDetailToReturn = new FighterDetail();
    fighterDetailToReturn.setId(detailId);
    fighterDetailToReturn.setOrigin(akumaOrigin);

    Mockito.when(fighterService.updateFighterDetail(eq(akumaId), any()))
      .thenReturn(fighterDetailToReturn);

    FighterDetailDto updatedFighterDetail = fighterController
      .updateFighterDetail(akumaId, fighterDetailCreationDto);

    Mockito.verify(fighterService).updateFighterDetail(eq(akumaId), any());

    assertEquals(detailId, updatedFighterDetail.id());
    assertEquals(akumaOrigin, updatedFighterDetail.origin());
  }

  @Test
  public void testRemoveFighterDetail() throws FighterNotFoundException, 
      FighterDetailNotFoundException {
    FighterDetail fighterDetailToReturn = new FighterDetail();
    fighterDetailToReturn.setId(detailId);
    fighterDetailToReturn.setOrigin(akumaOrigin);

    Mockito.when(fighterService.removFighterDetail(eq(akumaId)))
      .thenReturn(fighterDetailToReturn);

    FighterDetailDto removedFighterDetail = fighterController
      .removFighterDetailDto(akumaId);

    Mockito.verify(fighterService).removFighterDetail(eq(akumaId));

    assertEquals(detailId, removedFighterDetail.id());
    assertEquals(akumaOrigin, removedFighterDetail.origin());
  }

  @Test
  public void testSetFighterPublisher() throws FighterNotFoundException,
      PublisherNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Publisher publisher = new Publisher();
    publisher.setId(capcomId);
    publisher.setName(capcomName);
    publisher.setCountry(capcomCountry);

    fighterToReturn.setPublisher(publisher);

    Mockito.when(fighterService.setFighterPublisher(eq(akumaId), eq(capcomId)))
      .thenReturn(fighterToReturn);

    FighterDto returnedFighter = fighterController.setFighterPublisher(akumaId, capcomId);

    Mockito.verify(fighterService).setFighterPublisher(eq(akumaId), eq(capcomId));

    assertEquals(akumaId, returnedFighter.id());
    assertEquals(akumaName, returnedFighter.name());
    assertEquals(akumaGender, returnedFighter.gender());
    assertEquals(capcomId, returnedFighter.publisherDto().id());
    assertEquals(capcomName, returnedFighter.publisherDto().name());
    assertEquals(capcomCountry, returnedFighter.publisherDto().country());
  }

  @Test
  public void testRemoveFighterPublisher() throws FighterNotFoundException,
      PublisherNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.removeFighterPublisher(eq(akumaId)))
      .thenReturn(fighterToReturn);

    FighterDto returnedFighter = fighterController.removeFighterPublisher(akumaId);

    Mockito.verify(fighterService).removeFighterPublisher(eq(akumaId));

    assertEquals(akumaId, returnedFighter.id());
    assertEquals(akumaName, returnedFighter.name());
    assertEquals(akumaGender, returnedFighter.gender());
    assertEquals(valueNull, returnedFighter.publisherDto());
  }

  @Test
  public void testAddFighterGame() throws FighterNotFoundException,
      GameNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Game game = new Game();
    game.setId(streetFighters3Id);
    game.setTitle(streetFighters3Title);
    game.setYear(streetFighters3Year);

    fighterToReturn.setGames(new ArrayList<Game>(Arrays.asList(game)));

    Mockito.when(fighterService.addFighterGame(eq(akumaId), eq(streetFighters3Id)))
      .thenReturn(fighterToReturn);

    FighterDto returnedFighter = fighterController.addFighterGame(akumaId, streetFighters3Id);

    Mockito.verify(fighterService).addFighterGame(eq(akumaId), eq(streetFighters3Id));

    assertEquals(akumaId, returnedFighter.id());
    assertEquals(akumaName, returnedFighter.name());
    assertEquals(akumaGender, returnedFighter.gender());
    assertEquals(
      List.of(streetFighters3Id),
      returnedFighter.games().stream().map(GameDto::id).toList()
    );
    assertEquals(
      List.of(streetFighters3Title),
      returnedFighter.games().stream().map(GameDto::title).toList()
    );
    assertEquals(
      List.of(streetFighters3Year),
      returnedFighter.games().stream().map(GameDto::year).toList()
    );
  }

  @Test
  public void removeFighterGameA() throws FighterNotFoundException,
      GameNotFoundException {
    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterService.removeFighterGame(eq(akumaId), eq(streetFighters3Id)))
      .thenReturn(fighterToReturn);

    FighterDto returnedFighter = fighterController.removeFighterGame(akumaId, streetFighters3Id);

    Mockito.verify(fighterService).removeFighterGame(eq(akumaId), eq(streetFighters3Id));

    assertEquals(akumaId, returnedFighter.id());
    assertEquals(akumaName, returnedFighter.name());
    assertEquals(akumaGender, returnedFighter.gender());
    assertEquals(valueNull, returnedFighter.games());
  }

}
