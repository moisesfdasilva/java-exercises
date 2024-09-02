package moses.streetfighters.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import moses.streetfighters.entity.Fighter;
import moses.streetfighters.exception.FighterNotFoundException;
import moses.streetfighters.repository.FighterRepository;
import moses.streetfighters.service.FighterService;

@SpringBootTest
@ActiveProfiles("test")
public class FighterServiceTest {

  @Autowired
  FighterService fighterService;

  @MockBean
  FighterRepository fighterRepository;

  private final Long akumaId = 556L;
  private final String akumaName = "Akuma";
  private final String akumaGender = "male";

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
  
}
