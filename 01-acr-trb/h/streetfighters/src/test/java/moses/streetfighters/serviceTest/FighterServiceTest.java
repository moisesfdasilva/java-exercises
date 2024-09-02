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

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.entity.FighterDetail;
import moses.streetfighters.entity.Game;
import moses.streetfighters.entity.Publisher;
import moses.streetfighters.exception.FighterDetailNotFoundException;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.exception.GameNotFoundException;
import moses.streetfighters.exception.PublisherNotFoundException;
import moses.streetfighters.repository.FighterDetailRepository;
import moses.streetfighters.repository.FighterRepository;
import moses.streetfighters.repository.GameRepository;
import moses.streetfighters.repository.PublisherRepository;
import moses.streetfighters.service.FighterService;

@SpringBootTest
@ActiveProfiles("test")
public class FighterServiceTest {

  @Autowired
  FighterService fighterService;

  @MockBean
  FighterRepository fighterRepository;

  @MockBean
  FighterDetailRepository fighterDetailRepository;

  @MockBean
  PublisherRepository publisherRepository;

  @MockBean
  GameRepository gameRepository;

  private final Long akumaId = 556L;
  private final String akumaName = "Akuma";
  private final String akumaGender = "male";
  private final String akumaOrigin = "Japan";

  private final Long cammyId = 16L;
  private final String cammyName = "Cammy";
  private final String cammyGender = "female";
  private final String cammyOrigin = "England";

  private final Long detailId = 762L;

  private final Long publisherId = 223L;
  private final String publisherName = "Capcom";
  private final String publisherOrigin = "Japan";

  private final Long sf3Id = 16L;
  private final String sf3Title = "Street Fighters III";
  private final Integer sf3Year = 1997;

  private final Long sf6Id = 32L;
  private final String sf6Title = "Street Fighters 6";
  private final Integer sf6Year = 2023;

  @Test
  public void testFighterCreation() {
    Fighter fighter = new Fighter();
    fighter.setName(akumaName);
    fighter.setGender(akumaGender);

    Fighter fighterToReturn = new Fighter();
    fighterToReturn.setId(akumaId);
    fighterToReturn.setName(akumaName);
    fighterToReturn.setGender(akumaGender);

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterToReturn);

    Fighter savedFighter = fighterService.create(fighterToReturn);

    Mockito.verify(fighterRepository).save(any());

    assertEquals(akumaId, savedFighter.getId());
    assertEquals(fighter.getName(), savedFighter.getName());
    assertEquals(fighter.getGender(), savedFighter.getGender());
  }

  @Test
  public void testFighterRetrieval() throws FighterNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(akumaId);
    fighter.setName(akumaName);
    fighter.setGender(akumaGender);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighter));

    Fighter returnedFighter = fighterService.findById(akumaId);

    Mockito.verify(fighterRepository).findById(eq(akumaId));

    assertEquals(returnedFighter.getId(), fighter.getId());
    assertEquals(returnedFighter.getName(), fighter.getName());
    assertEquals(returnedFighter.getGender(), fighter.getGender());
  }

  @Test
  public void testFighterRetrivalNotFound() {
    Mockito.when(fighterRepository.findById(any()))
      .thenReturn(Optional.empty());

    assertThrows(FighterNotFoundException.class,
      () -> fighterService.findById(akumaId));

    Mockito.verify(fighterRepository).findById(eq(akumaId));
  }

  @Test
  public void testGetAll() {
    Fighter fighterA = new Fighter();
    fighterA.setId(akumaId);
    fighterA.setName(akumaName);
    fighterA.setGender(akumaGender);
    Fighter fighterB = new Fighter();
    fighterB.setId(cammyId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    List<Fighter> fighters = List.of(fighterA, fighterB);

    Mockito.when(fighterRepository.findAll()).thenReturn(fighters);

    List<Fighter> returnedFighters = fighterService.findAll();

    Mockito.verify(fighterRepository).findAll();

    assertEquals(
      returnedFighters.stream().map(Fighter::getId).toList(),
      fighters.stream().map(Fighter::getId).toList()
    );
  }

  @Test
  public void testUpdate() throws FighterNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(akumaId);
    fighterA.setName(akumaName);
    fighterA.setGender(akumaGender);
    Fighter fighterB = new Fighter();
    fighterB.setId(akumaId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighterA));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterB);

    Fighter savedFighter = fighterService.update(akumaId, fighterB);

    Mockito.verify(fighterRepository).findById(eq(akumaId));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(akumaId, savedFighter.getId());
    assertEquals(fighterB.getName(), savedFighter.getName());
    assertEquals(fighterB.getGender(), savedFighter.getGender());
  }

  @Test
  public void testDeleteById() throws FighterNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(cammyId);
    fighter.setName(cammyName);
    fighter.setGender(cammyGender);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighter));

    Mockito.doNothing().when(fighterRepository).deleteById(eq(cammyId));
    
    Fighter deletedFighter = fighterService.deleteById(cammyId);

    Mockito.verify(fighterRepository).findById(eq(cammyId));
    Mockito.verify(fighterRepository).deleteById(eq(cammyId));

    assertEquals(fighter.getId(), deletedFighter.getId());
    assertEquals(fighter.getName(), deletedFighter.getName());
    assertEquals(fighter.getGender(), deletedFighter.getGender());
  }

  @Test
  public void testCreateFighterDetail() throws FighterNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(cammyId);
    fighter.setName(cammyName);
    fighter.setGender(cammyGender);

    FighterDetail fighterDetail = new FighterDetail();
    fighterDetail.setId(detailId);
    fighterDetail.setOrigin(cammyOrigin);

    fighter.setDetails(fighterDetail);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighter));

    Mockito.when(fighterDetailRepository.save(any()))
      .thenReturn(fighterDetail);

    FighterDetail savedFighterDetail = fighterService.createFighterDetail(cammyId, fighterDetail);

    Mockito.verify(fighterRepository).findById(eq(cammyId));
    Mockito.verify(fighterDetailRepository).save(any());

    assertEquals(fighterDetail.getId(), savedFighterDetail.getId());
    assertEquals(fighterDetail.getOrigin(), savedFighterDetail.getOrigin());
  }

  @Test
  public void testGetFighterDetail() throws FighterNotFoundException, FighterDetailNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(cammyId);
    fighter.setName(cammyName);
    fighter.setGender(cammyGender);

    FighterDetail fighterDetail = new FighterDetail();
    fighterDetail.setId(detailId);
    fighterDetail.setOrigin(cammyOrigin);

    fighter.setDetails(fighterDetail);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighter));

    FighterDetail returnedFighterDetail = fighterService.getFighterDetail(cammyId);

    Mockito.verify(fighterRepository).findById(eq(cammyId));

    assertEquals(fighterDetail.getId(), returnedFighterDetail.getId());
    assertEquals(fighterDetail.getOrigin(), returnedFighterDetail.getOrigin());
  }

  @Test
  public void testFighterDetailNotFound() {
    Fighter fighter = new Fighter();
    fighter.setId(cammyId);
    fighter.setName(cammyName);
    fighter.setGender(cammyGender);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighter));

    assertThrows(FighterDetailNotFoundException.class,
      () -> fighterService.getFighterDetail(cammyId));

    Mockito.verify(fighterRepository).findById(eq(cammyId));
  }

  @Test
  public void testUpdateFighterDetail() throws FighterNotFoundException, FighterDetailNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(akumaId);
    fighter.setName(akumaName);
    fighter.setGender(akumaGender);

    FighterDetail fighterDetailA = new FighterDetail();
    fighterDetailA.setId(detailId);
    fighterDetailA.setOrigin(akumaOrigin);

    fighter.setDetails(fighterDetailA);

    FighterDetail fighterDetailB = new FighterDetail();
    fighterDetailB.setOrigin(cammyOrigin);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighter));

    Mockito.when(fighterDetailRepository.save(any()))
      .thenReturn(fighterDetailB);

    FighterDetail savedFighterDetail = fighterService.updateFighterDetail(akumaId, fighterDetailB);

    Mockito.verify(fighterRepository).findById(eq(akumaId));
    Mockito.verify(fighterDetailRepository).save(any());

    assertEquals(fighterDetailB.getOrigin(), savedFighterDetail.getOrigin());
  }

  @Test
  public void testRemoveFighterDetail() throws FighterNotFoundException, FighterDetailNotFoundException {
    Fighter fighter = new Fighter();
    fighter.setId(akumaId);
    fighter.setName(akumaName);
    fighter.setGender(akumaGender);

    FighterDetail fighterDetail = new FighterDetail();
    fighterDetail.setId(detailId);
    fighterDetail.setOrigin(akumaOrigin);

    fighter.setDetails(fighterDetail);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighter));

    Mockito.doNothing().when(fighterDetailRepository).delete(eq(fighterDetail));
    
    FighterDetail deletedFighterDetail = fighterService.removFighterDetail(akumaId);

    Mockito.verify(fighterRepository).findById(eq(akumaId));
    Mockito.verify(fighterDetailRepository).delete(eq(fighterDetail));

    assertEquals(fighterDetail.getId(), deletedFighterDetail.getId());
    assertEquals(fighterDetail.getOrigin(), deletedFighterDetail.getOrigin());
  }

  @Test
  public void testSetFighterPublisher() throws FighterNotFoundException, PublisherNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(akumaId);
    fighterA.setName(akumaName);
    fighterA.setGender(akumaGender);

    Publisher publisher = new Publisher();
    publisher.setId(publisherId);
		publisher.setName(publisherName);
		publisher.setCountry(publisherOrigin);

    fighterA.setPublisher(publisher);

    Fighter fighterB = new Fighter();
    fighterB.setId(akumaId);
    fighterB.setName(akumaName);
    fighterB.setGender(akumaGender);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighterB));

    Mockito.when(publisherRepository.findById(eq(publisherId)))
      .thenReturn(Optional.of(publisher));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterA);

    Fighter savedFighter = fighterService.setFighterPublisher(akumaId, publisherId);

    Mockito.verify(fighterRepository).findById(eq(akumaId));
    Mockito.verify(publisherRepository).findById(eq(publisherId));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(publisher.getId(), savedFighter.getPublisher().getId());
    assertEquals(publisher.getName(), savedFighter.getPublisher().getName());
    assertEquals(publisher.getCountry(), savedFighter.getPublisher().getCountry());
  }

  @Test
  public void testRemoveFighterPublisher() throws FighterNotFoundException, PublisherNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(akumaId);
    fighterA.setName(akumaName);
    fighterA.setGender(akumaGender);

    Publisher publisher = new Publisher();
    publisher.setId(publisherId);
		publisher.setName(publisherName);
		publisher.setCountry(publisherOrigin);

    fighterA.setPublisher(publisher);

    Fighter fighterB = new Fighter();
    fighterB.setId(akumaId);
    fighterB.setName(akumaName);
    fighterB.setGender(akumaGender);

    Mockito.when(fighterRepository.findById(eq(akumaId)))
      .thenReturn(Optional.of(fighterA));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterB);

    Fighter savedFighter = fighterService.removeFighterPublisher(akumaId);

    Mockito.verify(fighterRepository).findById(eq(akumaId));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(fighterB.getPublisher(), savedFighter.getPublisher());
  }

  @Test
  public void testAddFighterGameWithEmptyList() throws FighterNotFoundException, GameNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(cammyId);
    fighterA.setName(cammyName);
    fighterA.setGender(cammyGender);

    Game gameA = new Game();
    gameA.setId(sf3Id);
    gameA.setTitle(sf3Title);
    gameA.setYear(sf3Year);

    fighterA.setGames(List.of(gameA));

    Fighter fighterB = new Fighter();
    fighterB.setId(cammyId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighterB));

    Mockito.when(gameRepository.findById(eq(sf3Id)))
      .thenReturn(Optional.of(gameA));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterA);
    
    Fighter savedFighter = fighterService.addFighterGame(cammyId, sf3Id);

    Mockito.verify(fighterRepository).findById(eq(cammyId));
    Mockito.verify(gameRepository).findById(eq(sf3Id));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(
      List.of(gameA.getId()), 
      savedFighter.getGames().stream().map(Game::getId).toList()
    );
    assertEquals(
      List.of(gameA.getTitle()), 
      savedFighter.getGames().stream().map(Game::getTitle).toList()
    );
    assertEquals(
      List.of(gameA.getYear()), 
      savedFighter.getGames().stream().map(Game::getYear).toList()
    );
  }

  @Test
  public void testAddFighterGameWithNonEmptyList() throws FighterNotFoundException, GameNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(cammyId);
    fighterA.setName(cammyName);
    fighterA.setGender(cammyGender);

    Game gameA = new Game();
    gameA.setId(sf3Id);
    gameA.setTitle(sf3Title);
    gameA.setYear(sf3Year);

    Game gameB = new Game();
    gameB.setId(sf6Id);
    gameB.setTitle(sf6Title);
    gameB.setYear(sf6Year);

    fighterA.setGames(new ArrayList<Game>(Arrays.asList(gameA, gameB)));

    Fighter fighterB = new Fighter();
    fighterB.setId(cammyId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    fighterB.setGames(new ArrayList<Game>(Arrays.asList(gameA)));

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighterB));

    Mockito.when(gameRepository.findById(eq(sf6Id)))
      .thenReturn(Optional.of(gameB));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterA);

    Fighter savedFighter = fighterService.addFighterGame(cammyId, sf6Id);
    
    Mockito.verify(fighterRepository).findById(eq(cammyId));
    Mockito.verify(gameRepository).findById(eq(sf6Id));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(
      List.of(gameA.getId(), gameB.getId()), 
      savedFighter.getGames().stream().map(Game::getId).toList()
    );
    assertEquals(
      List.of(gameA.getTitle(), gameB.getTitle()),
      savedFighter.getGames().stream().map(Game::getTitle).toList()
    );
    assertEquals(
      List.of(gameA.getYear(), gameB.getYear()),
      savedFighter.getGames().stream().map(Game::getYear).toList()
    );
  }

  @Test
  public void testRemoveFighterGame() throws FighterNotFoundException, GameNotFoundException {
    Fighter fighterA = new Fighter();
    fighterA.setId(cammyId);
    fighterA.setName(cammyName);
    fighterA.setGender(cammyGender);

    Game game = new Game();
    game.setId(sf6Id);
    game.setTitle(sf6Title);
    game.setYear(sf6Year);

    fighterA.setGames(new ArrayList<Game>(Arrays.asList(game)));

    Fighter fighterB = new Fighter();
    fighterB.setId(cammyId);
    fighterB.setName(cammyName);
    fighterB.setGender(cammyGender);

    Mockito.when(fighterRepository.findById(eq(cammyId)))
      .thenReturn(Optional.of(fighterA));

    Mockito.when(gameRepository.findById(eq(sf6Id)))
      .thenReturn(Optional.of(game));

    Mockito.when(fighterRepository.save(any()))
      .thenReturn(fighterB);

    Fighter savedFighter = fighterService.removeFighterGame(cammyId, sf6Id);

    Mockito.verify(fighterRepository).findById(eq(cammyId));
    Mockito.verify(gameRepository).findById(eq(sf6Id));
    Mockito.verify(fighterRepository).save(any());

    assertEquals(fighterB.getGames(), savedFighter.getGames());
  }
}
